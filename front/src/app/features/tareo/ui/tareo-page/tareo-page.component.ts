import {
  ChangeDetectionStrategy,
  Component,
  inject,
  linkedSignal,
  signal,
} from '@angular/core';
import { rxResource } from '@angular/core/rxjs-interop';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Button } from 'primeng/button';
import { Dialog } from 'primeng/dialog';
import { InputNumber } from 'primeng/inputnumber';
import { Select } from 'primeng/select';
import { TableModule } from 'primeng/table';
import { Textarea } from 'primeng/textarea';
import { ToggleSwitch } from 'primeng/toggleswitch';
import { PERIODOS_API_PORT } from '@features/periodos/application/periodos-api.port';
import { AREAS_API_PORT } from '@features/areas/application/areas-api.port';
import { TAREO_API_PORT } from '../../application/tareo-api.port';
import {
  CATEGORIA_COLORS,
  GuardarAsistenciaItem,
  MatrizAsistencia,
  MatrizFila,
} from '../../domain/tareo.model';

@Component({
  selector: 'app-tareo-page',
  imports: [
    TableModule,
    Select,
    Button,
    Dialog,
    FormsModule,
    InputNumber,
    Textarea,
    ToggleSwitch,
    DatePipe,
  ],
  templateUrl: './tareo-page.component.html',
  styleUrl: './tareo-page.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TareoPageComponent {
  private readonly tareoApi = inject(TAREO_API_PORT);
  private readonly periodosApi = inject(PERIODOS_API_PORT);
  private readonly areasApi = inject(AREAS_API_PORT);
  private readonly messageService = inject(MessageService);

  protected readonly periodoId = signal<string | null>(null);
  protected readonly areaId = signal<string | null>(null);
  protected readonly quincena = signal<1 | 2>(1);
  protected readonly tareoId = signal<number | null>(null);

  protected readonly periodosResource = rxResource({
    stream: () => this.periodosApi.list(),
  });

  protected readonly areasResource = rxResource({
    stream: () => this.areasApi.list(),
  });

  protected readonly matrizResource = rxResource({
    params: () => {
      const id = this.tareoId();
      const q = this.quincena();
      if (!id) return undefined;
      return { id, q };
    },
    stream: ({ params }) => this.tareoApi.cargarMatriz(params.id, params.q),
  });

  protected readonly celdaEditando = linkedSignal({
    source: () => ({
      fila: null as MatrizFila | null,
      asistencia: null as MatrizAsistencia | null,
      diaLabel: '',
    }),
    computation: () => ({
      fila: null as MatrizFila | null,
      asistencia: null as MatrizAsistencia | null,
      diaLabel: '',
    }),
  });

  protected readonly detalleVisible = signal(false);
  protected readonly editCategoria = signal<string | null>(null);
  protected readonly editTurno = signal<string | null>(null);
  protected readonly editBn = signal(false);
  protected readonly editHeTotal = signal<number | null>(null);
  protected readonly editHe25 = signal<number | null>(null);
  protected readonly editHe30 = signal<number | null>(null);
  protected readonly editObs = signal('');

  protected readonly categorias = [
    'A', 'F', 'D', 'V', 'DM', 'AM', 'LCG', 'LSG', 'LPP', 'S', 'SP', 'CE', 'FT', 'FD', 'AV',
  ].map((c) => ({ label: c, value: c }));

  protected readonly turnos = [
    { label: 'Manana', value: 'MANANA' },
    { label: 'Tarde', value: 'TARDE' },
    { label: 'Noche', value: 'NOCHE' },
    { label: 'Partido', value: 'PARTIDO' },
  ];

  protected chipClass(codigo: string | null): string {
    if (!codigo) return 'bg-slate-50 text-slate-400 border border-dashed border-slate-300';
    return CATEGORIA_COLORS[codigo] ?? CATEGORIA_COLORS['default'];
  }

  protected habilitarTareo(): void {
    const periodoId = this.periodoId();
    const areaId = this.areaId();
    if (!periodoId || !areaId) return;

    this.tareoApi.habilitar(periodoId, areaId).subscribe({
      next: (t) => {
        this.tareoId.set(Number(t.id));
        this.messageService.add({
          severity: 'success',
          summary: 'Tareo habilitado',
          detail: 'Se cargo el snapshot de colaboradores activos.',
        });
      },
      error: (err) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: err?.error?.message ?? 'No se pudo habilitar el tareo.',
        });
      },
    });
  }

  protected abrirDetalle(fila: MatrizFila, asistencia: MatrizAsistencia, diaLabel: string): void {
    if (this.matrizResource.value()?.bloqueada) return;
    this.celdaEditando.set({ fila, asistencia, diaLabel });
    this.editCategoria.set(asistencia.categoriaCodigo);
    this.editTurno.set(asistencia.turnoId);
    this.editBn.set(asistencia.bonificacionNocturna);
    this.editHeTotal.set(asistencia.heTotal);
    this.editHe25.set(asistencia.he25);
    this.editHe30.set(asistencia.he30);
    this.editObs.set(asistencia.observacion ?? '');
    this.detalleVisible.set(true);
  }

  protected guardarDetalle(): void {
    const ctx = this.celdaEditando();
    const tareoId = this.tareoId();
    if (!ctx.fila || !ctx.asistencia || !tareoId) return;

    const item: GuardarAsistenciaItem = {
      tareoColaboradorId: ctx.fila.tareoColaboradorId,
      periodoDiaId: ctx.asistencia.periodoDiaId,
      categoriaCodigo: this.editCategoria(),
      turnoId: this.editTurno(),
      bonificacionNocturna: this.editBn(),
      heTotal: this.editHeTotal(),
      he25: this.editHe25(),
      he30: this.editHe30(),
      observacion: this.editObs() || null,
    };

    this.tareoApi.guardarAsistencias(tareoId, this.quincena(), [item]).subscribe({
      next: () => {
        this.detalleVisible.set(false);
        this.matrizResource.reload();
        this.messageService.add({ severity: 'success', summary: 'Guardado' });
      },
    });
  }

  protected culminar(): void {
    const tareoId = this.tareoId();
    if (!tareoId) return;
    this.tareoApi.culminarQuincena(tareoId, this.quincena()).subscribe({
      next: () => {
        this.matrizResource.reload();
        this.messageService.add({
          severity: 'success',
          summary: 'Quincena culminada',
          detail: 'Se notifico a RRHH.',
        });
      },
      error: (err) => {
        this.messageService.add({
          severity: 'error',
          summary: 'No se pudo culminar',
          detail: err?.error?.message ?? 'Revise registros pendientes.',
        });
      },
    });
  }
}

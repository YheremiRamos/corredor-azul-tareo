import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { rxResource } from '@angular/core/rxjs-interop';
import { FormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Button } from 'primeng/button';
import { Card } from 'primeng/card';
import { InputNumber } from 'primeng/inputnumber';
import { TableModule } from 'primeng/table';
import { PERIODOS_API_PORT } from '../../application/periodos-api.port';

@Component({
  selector: 'app-periodos-list',
  imports: [Card, TableModule, Button, InputNumber, FormsModule],
  templateUrl: './periodos-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PeriodosListComponent {
  private readonly periodosApi = inject(PERIODOS_API_PORT);
  private readonly messageService = inject(MessageService);

  protected readonly anio = signal(new Date().getFullYear());
  protected readonly mes = signal(new Date().getMonth() + 1);
  protected readonly creando = signal(false);

  protected readonly periodosResource = rxResource({
    stream: () => this.periodosApi.list(),
  });

  protected crearPeriodo(): void {
    this.creando.set(true);
    this.periodosApi.create({ anio: this.anio(), mes: this.mes() }).subscribe({
      next: () => {
        this.creando.set(false);
        this.periodosResource.reload();
        this.messageService.add({
          severity: 'success',
          summary: 'Período creado',
          detail: 'Se generaron los días del ciclo 22→21.',
        });
      },
      error: (err) => {
        this.creando.set(false);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: err?.error?.message ?? 'No se pudo crear el período.',
        });
      },
    });
  }
}

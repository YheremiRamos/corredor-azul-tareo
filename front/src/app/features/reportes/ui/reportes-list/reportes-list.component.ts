import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { rxResource } from '@angular/core/rxjs-interop';
import { MessageService } from 'primeng/api';
import { Button } from 'primeng/button';
import { Card } from 'primeng/card';
import { REPORTES_API_PORT } from '../../application/reportes-api.port';

@Component({
  selector: 'app-reportes-list',
  imports: [Card, Button],
  templateUrl: './reportes-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReportesListComponent {
  private readonly reportesApi = inject(REPORTES_API_PORT);
  private readonly messageService = inject(MessageService);

  protected readonly reportesResource = rxResource({
    stream: () => this.reportesApi.list(),
  });

  protected exportar(formato: 'excel' | 'pdf' | 'txt'): void {
    this.reportesApi.exportar(formato).subscribe({
      next: (blob) => {
        const url = URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = `reporte.${formato === 'excel' ? 'xlsx' : formato}`;
        link.click();
        URL.revokeObjectURL(url);
        this.messageService.add({
          severity: 'success',
          summary: 'Reporte generado',
        });
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error al exportar',
        });
      },
    });
  }
}

import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { rxResource } from '@angular/core/rxjs-interop';
import { COLABORADORES_API_PORT } from '../../application/colaboradores-api.port';
import { Card } from 'primeng/card';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-colaboradores-list',
  imports: [Card, TableModule],
  templateUrl: './colaboradores-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ColaboradoresListComponent {
  private readonly colaboradoresApi = inject(COLABORADORES_API_PORT);

  protected readonly colaboradoresResource = rxResource({
    stream: () => this.colaboradoresApi.list(),
  });
}

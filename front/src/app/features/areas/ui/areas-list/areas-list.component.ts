import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { rxResource } from '@angular/core/rxjs-interop';
import { AREAS_API_PORT } from '../../application/areas-api.port';
import { Card } from 'primeng/card';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-areas-list',
  imports: [Card, TableModule],
  templateUrl: './areas-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AreasListComponent {
  private readonly areasApi = inject(AREAS_API_PORT);

  protected readonly areasResource = rxResource({
    stream: () => this.areasApi.list(),
  });
}

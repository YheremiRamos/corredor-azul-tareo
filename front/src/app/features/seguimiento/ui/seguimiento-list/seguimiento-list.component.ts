import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { rxResource } from '@angular/core/rxjs-interop';
import { SEGUIMIENTO_API_PORT } from '../../application/seguimiento-api.port';
import { Card } from 'primeng/card';

@Component({
  selector: 'app-seguimiento-list',
  imports: [Card],
  templateUrl: './seguimiento-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SeguimientoListComponent {
  private readonly seguimientoApi = inject(SEGUIMIENTO_API_PORT);

  protected readonly seguimientoResource = rxResource({
    stream: () => this.seguimientoApi.list(),
  });
}

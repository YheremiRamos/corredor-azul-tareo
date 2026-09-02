import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { rxResource } from '@angular/core/rxjs-interop';
import { USUARIOS_API_PORT } from '../../application/usuarios-api.port';
import { Card } from 'primeng/card';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-usuarios-list',
  imports: [Card, TableModule],
  templateUrl: './usuarios-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class UsuariosListComponent {
  private readonly usuariosApi = inject(USUARIOS_API_PORT);

  protected readonly usuariosResource = rxResource({
    stream: () => this.usuariosApi.list(),
  });
}

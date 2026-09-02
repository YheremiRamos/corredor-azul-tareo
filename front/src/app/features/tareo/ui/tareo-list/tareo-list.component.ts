import {
  ChangeDetectionStrategy,
  Component,
  inject,
  linkedSignal,
  signal,
} from '@angular/core';
import { rxResource } from '@angular/core/rxjs-interop';
import { TAREO_API_PORT } from '../../application/tareo-api.port';
import { Card } from 'primeng/card';
import { Select } from 'primeng/select';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-tareo-list',
  imports: [Card, TableModule, Select],
  templateUrl: './tareo-list.component.html',
  styleUrl: './tareo-list.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TareoListComponent {
  private readonly tareoApi = inject(TAREO_API_PORT);

  protected readonly estadoFiltro = signal<string | null>(null);

  protected readonly tareoResource = rxResource({
    stream: () => this.tareoApi.list(),
  });

  protected readonly tareosFiltrados = linkedSignal({
    source: () => ({
      items: this.tareoResource.value() ?? [],
      filtro: this.estadoFiltro(),
    }),
    computation: ({ items, filtro }) =>
      filtro ? items.filter((item) => item.estado === filtro) : items,
  });

  protected readonly estados = [
    { label: 'Todos', value: null },
    { label: 'Borrador', value: 'BORRADOR' },
    { label: 'Enviado', value: 'ENVIADO' },
    { label: 'Cerrado', value: 'CERRADO' },
  ];
}

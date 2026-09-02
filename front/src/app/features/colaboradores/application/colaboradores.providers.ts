import { Provider } from '@angular/core';
import { COLABORADORES_API_PORT } from './colaboradores-api.port';
import { ColaboradoresHttpAdapter } from '../infrastructure/colaboradores-http.adapter';

export const colaboradoresProviders: Provider[] = [
  ColaboradoresHttpAdapter,
  { provide: COLABORADORES_API_PORT, useExisting: ColaboradoresHttpAdapter },
];

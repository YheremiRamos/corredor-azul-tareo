import { Provider } from '@angular/core';
import { PERIODOS_API_PORT } from './periodos-api.port';
import { PeriodosHttpAdapter } from '../infrastructure/periodos-http.adapter';

export const periodosProviders: Provider[] = [
  PeriodosHttpAdapter,
  { provide: PERIODOS_API_PORT, useExisting: PeriodosHttpAdapter },
];

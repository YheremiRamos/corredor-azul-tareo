import { Provider } from '@angular/core';
import { SEGUIMIENTO_API_PORT } from './seguimiento-api.port';
import { SeguimientoHttpAdapter } from '../infrastructure/seguimiento-http.adapter';

export const seguimientoProviders: Provider[] = [
  SeguimientoHttpAdapter,
  { provide: SEGUIMIENTO_API_PORT, useExisting: SeguimientoHttpAdapter },
];

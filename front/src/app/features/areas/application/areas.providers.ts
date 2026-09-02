import { Provider } from '@angular/core';
import { AREAS_API_PORT } from './areas-api.port';
import { AreasHttpAdapter } from '../infrastructure/areas-http.adapter';

export const areasProviders: Provider[] = [
  AreasHttpAdapter,
  { provide: AREAS_API_PORT, useExisting: AreasHttpAdapter },
];

import { Provider } from '@angular/core';
import { TAREO_API_PORT } from './tareo-api.port';
import { TareoHttpAdapter } from '../infrastructure/tareo-http.adapter';

export const tareoProviders: Provider[] = [
  TareoHttpAdapter,
  { provide: TAREO_API_PORT, useExisting: TareoHttpAdapter },
];

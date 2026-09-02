import { Provider } from '@angular/core';
import { REPORTES_API_PORT } from './reportes-api.port';
import { ReportesHttpAdapter } from '../infrastructure/reportes-http.adapter';

export const reportesProviders: Provider[] = [
  ReportesHttpAdapter,
  { provide: REPORTES_API_PORT, useExisting: ReportesHttpAdapter },
];

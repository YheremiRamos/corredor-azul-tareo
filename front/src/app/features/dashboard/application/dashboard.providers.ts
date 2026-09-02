import { Provider } from '@angular/core';
import { DASHBOARD_API_PORT } from './dashboard-api.port';
import { DashboardHttpAdapter } from '../infrastructure/dashboard-http.adapter';

export const dashboardProviders: Provider[] = [
  DashboardHttpAdapter,
  { provide: DASHBOARD_API_PORT, useExisting: DashboardHttpAdapter },
];

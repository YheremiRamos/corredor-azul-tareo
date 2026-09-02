import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';
import { DashboardStats } from '../domain/dashboard.model';

export interface DashboardApiPort {
  getStats(): Observable<DashboardStats>;
}

export const DASHBOARD_API_PORT = new InjectionToken<DashboardApiPort>('DASHBOARD_API_PORT');

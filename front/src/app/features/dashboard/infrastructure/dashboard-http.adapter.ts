import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { DashboardApiPort } from '../application/dashboard-api.port';
import { DashboardStats } from '../domain/dashboard.model';

@Injectable({ providedIn: 'root' })
export class DashboardHttpAdapter implements DashboardApiPort {
  private readonly http = inject(HttpClient);

  getStats(): Observable<DashboardStats> {
    return this.http.get<DashboardStats>(`${environment.apiUrl}/dashboard/stats`);
  }
}

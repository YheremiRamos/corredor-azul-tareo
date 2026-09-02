import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { ReportesApiPort } from '../application/reportes-api.port';
import { Reporte } from '../domain/reporte.model';

@Injectable({ providedIn: 'root' })
export class ReportesHttpAdapter implements ReportesApiPort {
  private readonly http = inject(HttpClient);

  list(): Observable<Reporte[]> {
    return this.http.get<Reporte[]>(`${environment.apiUrl}/reportes`);
  }

  exportar(
    formato: 'excel' | 'pdf' | 'txt',
    periodoId?: string,
    areaId?: string,
  ): Observable<Blob> {
    const params: Record<string, string> = { formato };
    if (periodoId) params['periodoId'] = periodoId;
    if (areaId) params['areaId'] = areaId;
    return this.http.get(`${environment.apiUrl}/reportes/export`, {
      params,
      responseType: 'blob',
    });
  }
}

import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';
import { Reporte } from '../domain/reporte.model';

export interface ReportesApiPort {
  list(): Observable<Reporte[]>;
  exportar(formato: 'excel' | 'pdf' | 'txt', periodoId?: string, areaId?: string): Observable<Blob>;
}

export const REPORTES_API_PORT = new InjectionToken<ReportesApiPort>('REPORTES_API_PORT');

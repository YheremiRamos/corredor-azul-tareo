import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';
import { Periodo } from '../domain/periodo.model';

export interface CrearPeriodoRequest {
  anio: number;
  mes: number;
}

export interface PeriodosApiPort {
  list(): Observable<Periodo[]>;
  create(request: CrearPeriodoRequest): Observable<Periodo>;
}

export const PERIODOS_API_PORT = new InjectionToken<PeriodosApiPort>('PERIODOS_API_PORT');

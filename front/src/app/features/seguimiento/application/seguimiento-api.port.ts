import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';
import { SeguimientoItem } from '../domain/seguimiento.model';

export interface SeguimientoApiPort {
  list(): Observable<SeguimientoItem[]>;
}

export const SEGUIMIENTO_API_PORT = new InjectionToken<SeguimientoApiPort>('SEGUIMIENTO_API_PORT');

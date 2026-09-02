import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';
import { Area } from '../domain/area.model';

export interface AreasApiPort {
  list(): Observable<Area[]>;
}

export const AREAS_API_PORT = new InjectionToken<AreasApiPort>('AREAS_API_PORT');

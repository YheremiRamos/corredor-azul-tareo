import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';
import {
  GuardarAsistenciaItem,
  MatrizTareo,
  TareoResumen,
} from '../domain/tareo.model';

export interface TareoApiPort {
  list(): Observable<TareoResumen[]>;
  habilitar(periodoId: number | string, areaId: string, subareaId?: string): Observable<TareoResumen>;
  cargarMatriz(tareoId: number, quincena: number): Observable<MatrizTareo>;
  guardarAsistencias(
    tareoId: number,
    quincena: number,
    asistencias: GuardarAsistenciaItem[],
  ): Observable<void>;
  culminarQuincena(tareoId: number, quincena: number): Observable<TareoResumen>;
}

export const TAREO_API_PORT = new InjectionToken<TareoApiPort>('TAREO_API_PORT');

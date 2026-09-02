import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { TareoApiPort } from '../application/tareo-api.port';
import {
  GuardarAsistenciaItem,
  MatrizTareo,
  TareoResumen,
} from '../domain/tareo.model';

@Injectable({ providedIn: 'root' })
export class TareoHttpAdapter implements TareoApiPort {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = `${environment.apiUrl}/tareo`;

  list(): Observable<TareoResumen[]> {
    return this.http.get<TareoResumen[]>(this.baseUrl);
  }

  habilitar(periodoId: number | string, areaId: string, subareaId?: string): Observable<TareoResumen> {
    return this.http.post<TareoResumen>(`${this.baseUrl}/habilitar`, {
      periodoId: Number(periodoId),
      areaId,
      subareaId: subareaId ?? null,
    });
  }

  cargarMatriz(tareoId: number, quincena: number): Observable<MatrizTareo> {
    return this.http.get<MatrizTareo>(`${this.baseUrl}/${tareoId}/matriz`, {
      params: { quincena: String(quincena) },
    });
  }

  guardarAsistencias(
    tareoId: number,
    quincena: number,
    asistencias: GuardarAsistenciaItem[],
  ): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${tareoId}/asistencias`, {
      quincena,
      asistencias,
    });
  }

  culminarQuincena(tareoId: number, quincena: number): Observable<TareoResumen> {
    return this.http.post<TareoResumen>(`${this.baseUrl}/${tareoId}/culminar`, null, {
      params: { quincena: String(quincena) },
    });
  }
}

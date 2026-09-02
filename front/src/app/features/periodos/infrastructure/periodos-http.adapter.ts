import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { PeriodosApiPort, CrearPeriodoRequest } from '../application/periodos-api.port';
import { Periodo } from '../domain/periodo.model';

@Injectable({ providedIn: 'root' })
export class PeriodosHttpAdapter implements PeriodosApiPort {
  private readonly http = inject(HttpClient);

  list(): Observable<Periodo[]> {
    return this.http.get<Periodo[]>(`${environment.apiUrl}/periodos`);
  }

  create(request: CrearPeriodoRequest): Observable<Periodo> {
    return this.http.post<Periodo>(`${environment.apiUrl}/periodos`, request);
  }
}

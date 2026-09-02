import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { SeguimientoApiPort } from '../application/seguimiento-api.port';
import { SeguimientoItem } from '../domain/seguimiento.model';

@Injectable({ providedIn: 'root' })
export class SeguimientoHttpAdapter implements SeguimientoApiPort {
  private readonly http = inject(HttpClient);

  list(): Observable<SeguimientoItem[]> {
    return this.http.get<SeguimientoItem[]>(`${environment.apiUrl}/seguimiento`);
  }
}

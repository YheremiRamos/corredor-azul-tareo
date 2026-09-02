import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { AreasApiPort } from '../application/areas-api.port';
import { Area } from '../domain/area.model';

@Injectable({ providedIn: 'root' })
export class AreasHttpAdapter implements AreasApiPort {
  private readonly http = inject(HttpClient);

  list(): Observable<Area[]> {
    return this.http.get<Area[]>(`${environment.apiUrl}/areas`);
  }
}

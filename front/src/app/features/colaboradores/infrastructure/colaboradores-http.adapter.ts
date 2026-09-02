import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { ColaboradoresApiPort } from '../application/colaboradores-api.port';
import { Colaborador } from '../domain/colaborador.model';

@Injectable({ providedIn: 'root' })
export class ColaboradoresHttpAdapter implements ColaboradoresApiPort {
  private readonly http = inject(HttpClient);

  list(): Observable<Colaborador[]> {
    return this.http.get<Colaborador[]>(`${environment.apiUrl}/colaboradores`);
  }
}

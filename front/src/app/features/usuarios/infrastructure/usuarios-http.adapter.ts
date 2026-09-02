import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';
import { UsuariosApiPort } from '../application/usuarios-api.port';
import { Usuario } from '../domain/usuario.model';

@Injectable({ providedIn: 'root' })
export class UsuariosHttpAdapter implements UsuariosApiPort {
  private readonly http = inject(HttpClient);

  list(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${environment.apiUrl}/usuarios`);
  }
}

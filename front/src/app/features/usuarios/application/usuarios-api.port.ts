import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../domain/usuario.model';

export interface UsuariosApiPort {
  list(): Observable<Usuario[]>;
}

export const USUARIOS_API_PORT = new InjectionToken<UsuariosApiPort>('USUARIOS_API_PORT');

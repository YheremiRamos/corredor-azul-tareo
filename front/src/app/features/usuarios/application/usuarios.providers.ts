import { Provider } from '@angular/core';
import { USUARIOS_API_PORT } from './usuarios-api.port';
import { UsuariosHttpAdapter } from '../infrastructure/usuarios-http.adapter';

export const usuariosProviders: Provider[] = [
  UsuariosHttpAdapter,
  { provide: USUARIOS_API_PORT, useExisting: UsuariosHttpAdapter },
];

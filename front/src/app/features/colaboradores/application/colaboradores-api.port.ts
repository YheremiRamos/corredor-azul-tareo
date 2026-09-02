import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';
import { Colaborador } from '../domain/colaborador.model';

export interface ColaboradoresApiPort {
  list(): Observable<Colaborador[]>;
}

export const COLABORADORES_API_PORT = new InjectionToken<ColaboradoresApiPort>('COLABORADORES_API_PORT');

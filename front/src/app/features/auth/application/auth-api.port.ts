import { InjectionToken } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginCredentials, LoginResponse } from '../domain/user.model';

export interface AuthApiPort {
  login(credentials: LoginCredentials): Observable<LoginResponse>;
  logout(): Observable<void>;
}

export const AUTH_API_PORT = new InjectionToken<AuthApiPort>('AUTH_API_PORT');

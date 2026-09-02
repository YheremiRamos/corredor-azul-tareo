import { Provider } from '@angular/core';
import { AUTH_API_PORT } from './auth-api.port';
import { AuthHttpAdapter } from '../infrastructure/auth-http.adapter';

export const authProviders: Provider[] = [
  AuthHttpAdapter,
  { provide: AUTH_API_PORT, useExisting: AuthHttpAdapter },
];

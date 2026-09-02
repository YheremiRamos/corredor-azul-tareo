export type UserRole = 'RRHH' | 'RESPONSABLE';

export interface AuthSession {
  token: string;
  userId: string;
  nombre: string;
  email: string;
  rol: UserRole;
}

export interface LoginCredentials {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  userId: string;
  nombre: string;
  email: string;
  rol: UserRole;
}

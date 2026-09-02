import { computed, inject, Injectable, signal } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, of, tap } from 'rxjs';
import { AUTH_API_PORT } from '@features/auth/application/auth-api.port';
import {
  AuthSession,
  LoginCredentials,
  UserRole,
} from '@features/auth/domain/user.model';

const SESSION_KEY = 'tareo_session';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly authApi = inject(AUTH_API_PORT);
  private readonly router = inject(Router);

  private readonly session = signal<AuthSession | null>(this.loadSession());

  readonly currentUser = computed(() => this.session());
  readonly isAuthenticated = computed(() => this.session() !== null);
  readonly userRole = computed(() => this.session()?.rol ?? null);
  readonly isRrhh = computed(() => this.userRole() === 'RRHH');
  readonly isResponsable = computed(() => this.userRole() === 'RESPONSABLE');

  login(credentials: LoginCredentials) {
    return this.authApi.login(credentials).pipe(
      tap((response) => {
        const session: AuthSession = {
          token: response.token,
          userId: response.userId,
          nombre: response.nombre,
          email: response.email,
          rol: response.rol,
        };
        this.persistSession(session);
        this.session.set(session);
      }),
      catchError((error) => {
        this.clearSession();
        throw error;
      }),
    );
  }

  logout(): void {
    this.authApi.logout().pipe(catchError(() => of(void 0))).subscribe(() => {
      this.clearSession();
      void this.router.navigate(['/login']);
    });
  }

  getToken(): string | null {
    return this.session()?.token ?? null;
  }

  hasRole(roles: UserRole[]): boolean {
    const role = this.userRole();
    return role !== null && roles.includes(role);
  }

  private loadSession(): AuthSession | null {
    const raw = localStorage.getItem(SESSION_KEY);
    if (!raw) {
      return null;
    }

    try {
      return JSON.parse(raw) as AuthSession;
    } catch {
      return null;
    }
  }

  private persistSession(session: AuthSession): void {
    localStorage.setItem(SESSION_KEY, JSON.stringify(session));
  }

  private clearSession(): void {
    localStorage.removeItem(SESSION_KEY);
    this.session.set(null);
  }
}

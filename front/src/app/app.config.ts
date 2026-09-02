import { ApplicationConfig, provideZonelessChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { providePrimeNG } from 'primeng/config';
import Aura from '@primeng/themes/aura';
import { definePreset } from '@primeng/themes';
import { routes } from './app.routes';
import { authInterceptor } from '@core/interceptors/auth.interceptor';
import { authProviders } from '@features/auth/application/auth.providers';
import { dashboardProviders } from '@features/dashboard/application/dashboard.providers';
import { tareoProviders } from '@features/tareo/application/tareo.providers';
import { periodosProviders } from '@features/periodos/application/periodos.providers';
import { colaboradoresProviders } from '@features/colaboradores/application/colaboradores.providers';
import { areasProviders } from '@features/areas/application/areas.providers';
import { usuariosProviders } from '@features/usuarios/application/usuarios.providers';
import { seguimientoProviders } from '@features/seguimiento/application/seguimiento.providers';
import { reportesProviders } from '@features/reportes/application/reportes.providers';
import { MessageService } from 'primeng/api';

/** Paleta Corredor Azul — CTA Arequipa */
const CorredorAzulPreset = definePreset(Aura, {
  semantic: {
    primary: {
      50: '#e8f1fb',
      100: '#d1e3f7',
      200: '#a3c7ef',
      300: '#75abe7',
      400: '#3d8ede',
      500: '#0066cc',
      600: '#0052a3',
      700: '#003d7a',
      800: '#002952',
      900: '#001429',
      950: '#000a14',
    },
  },
});

export const appConfig: ApplicationConfig = {
  providers: [
    provideZonelessChangeDetection(),
    provideRouter(routes),
    provideHttpClient(withInterceptors([authInterceptor])),
    provideAnimationsAsync(),
    providePrimeNG({
      theme: {
        preset: CorredorAzulPreset,
        options: {
          darkModeSelector: '.dark',
        },
      },
    }),
    MessageService,
    ...authProviders,
    ...dashboardProviders,
    ...tareoProviders,
    ...periodosProviders,
    ...colaboradoresProviders,
    ...areasProviders,
    ...usuariosProviders,
    ...seguimientoProviders,
    ...reportesProviders,
  ],
};

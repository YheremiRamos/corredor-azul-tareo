import { Routes } from '@angular/router';
import { authGuard, guestGuard } from '@core/guards/auth.guard';
import { roleGuard } from '@core/guards/role.guard';

export const routes: Routes = [
  {
    path: 'login',
    canActivate: [guestGuard],
    loadComponent: () =>
      import('@features/auth/ui/login/login.component').then((m) => m.LoginComponent),
  },
  {
    path: '',
    loadComponent: () =>
      import('@core/layout/main-layout/main-layout.component').then(
        (m) => m.MainLayoutComponent,
      ),
    canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      {
        path: 'dashboard',
        loadComponent: () =>
          import('@features/dashboard/ui/dashboard/dashboard.component').then(
            (m) => m.DashboardComponent,
          ),
      },
      {
        path: 'tareo',
        loadComponent: () =>
          import('@features/tareo/ui/tareo-page/tareo-page.component').then(
            (m) => m.TareoPageComponent,
          ),
      },
      {
        path: 'periodos',
        loadComponent: () =>
          import('@features/periodos/ui/periodos-list/periodos-list.component').then(
            (m) => m.PeriodosListComponent,
          ),
      },
      {
        path: 'colaboradores',
        loadComponent: () =>
          import('@features/colaboradores/ui/colaboradores-list/colaboradores-list.component').then(
            (m) => m.ColaboradoresListComponent,
          ),
      },
      {
        path: 'areas',
        canActivate: [roleGuard(['RRHH'])],
        loadComponent: () =>
          import('@features/areas/ui/areas-list/areas-list.component').then(
            (m) => m.AreasListComponent,
          ),
      },
      {
        path: 'usuarios',
        canActivate: [roleGuard(['RRHH'])],
        loadComponent: () =>
          import('@features/usuarios/ui/usuarios-list/usuarios-list.component').then(
            (m) => m.UsuariosListComponent,
          ),
      },
      {
        path: 'seguimiento',
        loadComponent: () =>
          import('@features/seguimiento/ui/seguimiento-list/seguimiento-list.component').then(
            (m) => m.SeguimientoListComponent,
          ),
      },
      {
        path: 'reportes',
        loadComponent: () =>
          import('@features/reportes/ui/reportes-list/reportes-list.component').then(
            (m) => m.ReportesListComponent,
          ),
      },
    ],
  },
  { path: '**', redirectTo: 'dashboard' },
];

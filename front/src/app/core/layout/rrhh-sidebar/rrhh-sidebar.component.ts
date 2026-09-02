import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '@core/services/auth.service';

interface NavItem {
  label: string;
  route: string;
  icon: string;
}

@Component({
  selector: 'app-rrhh-sidebar',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './rrhh-sidebar.component.html',
  styleUrl: './rrhh-sidebar.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RrhhSidebarComponent {
  protected readonly authService = inject(AuthService);

  protected readonly navItems: NavItem[] = [
    { label: 'Dashboard', route: '/dashboard', icon: 'pi pi-home' },
    { label: 'Tareo', route: '/tareo', icon: 'pi pi-table' },
    { label: 'Períodos', route: '/periodos', icon: 'pi pi-calendar' },
    { label: 'Colaboradores', route: '/colaboradores', icon: 'pi pi-users' },
    { label: 'Seguimiento', route: '/seguimiento', icon: 'pi pi-chart-line' },
    { label: 'Reportes', route: '/reportes', icon: 'pi pi-file-export' },
    { label: 'Usuarios', route: '/usuarios', icon: 'pi pi-user-edit' },
    { label: 'Áreas', route: '/areas', icon: 'pi pi-building' },
  ];

  protected userInitials(): string {
    const nombre = this.authService.currentUser()?.nombre ?? '';
    const parts = nombre.trim().split(/\s+/).filter(Boolean);
    if (parts.length >= 2) {
      return (parts[0][0] + parts[1][0]).toUpperCase();
    }
    return nombre.slice(0, 2).toUpperCase() || 'U';
  }
}

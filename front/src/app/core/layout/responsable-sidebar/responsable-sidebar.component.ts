import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '@core/services/auth.service';

interface NavItem {
  label: string;
  route: string;
  icon: string;
}

@Component({
  selector: 'app-responsable-sidebar',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './responsable-sidebar.component.html',
  styleUrl: './responsable-sidebar.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ResponsableSidebarComponent {
  protected readonly authService = inject(AuthService);

  protected readonly navItems: NavItem[] = [
    { label: 'Dashboard', route: '/dashboard', icon: 'pi pi-home' },
    { label: 'Tareo', route: '/tareo', icon: 'pi pi-table' },
    { label: 'Colaboradores', route: '/colaboradores', icon: 'pi pi-users' },
    { label: 'Seguimiento', route: '/seguimiento', icon: 'pi pi-chart-line' },
    { label: 'Reportes', route: '/reportes', icon: 'pi pi-file-export' },
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

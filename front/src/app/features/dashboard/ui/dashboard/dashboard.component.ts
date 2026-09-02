import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { rxResource } from '@angular/core/rxjs-interop';
import { AuthService } from '@core/services/auth.service';
import { DASHBOARD_API_PORT } from '../../application/dashboard-api.port';
import { ProgressSpinner } from 'primeng/progressspinner';

@Component({
  selector: 'app-dashboard',
  imports: [ProgressSpinner],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DashboardComponent {
  private readonly dashboardApi = inject(DASHBOARD_API_PORT);
  protected readonly authService = inject(AuthService);

  protected readonly statsResource = rxResource({
    stream: () => this.dashboardApi.getStats(),
  });

  protected greeting(): string {
    const hour = new Date().getHours();
    if (hour < 12) return 'Buenos días';
    if (hour < 18) return 'Buenas tardes';
    return 'Buenas noches';
  }
}

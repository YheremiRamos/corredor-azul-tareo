import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '@core/services/auth.service';
import { ThemeService } from '@core/services/theme.service';
import { Button } from 'primeng/button';
import { ToggleSwitch } from 'primeng/toggleswitch';
import { Tooltip } from 'primeng/tooltip';

@Component({
  selector: 'app-header',
  imports: [FormsModule, Button, ToggleSwitch, Tooltip],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HeaderComponent {
  protected readonly authService = inject(AuthService);
  protected readonly themeService = inject(ThemeService);

  protected userInitials(): string {
    const nombre = this.authService.currentUser()?.nombre ?? '';
    const parts = nombre.trim().split(/\s+/).filter(Boolean);
    if (parts.length >= 2) {
      return (parts[0][0] + parts[1][0]).toUpperCase();
    }
    return nombre.slice(0, 2).toUpperCase() || 'U';
  }

  protected onDarkModeChange(enabled: boolean): void {
    this.themeService.setDarkMode(enabled);
  }

  protected logout(): void {
    this.authService.logout();
  }
}

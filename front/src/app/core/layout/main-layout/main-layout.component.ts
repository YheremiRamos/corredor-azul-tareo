import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from '@core/services/auth.service';
import { HeaderComponent } from '../header/header.component';
import { ResponsableSidebarComponent } from '../responsable-sidebar/responsable-sidebar.component';
import { RrhhSidebarComponent } from '../rrhh-sidebar/rrhh-sidebar.component';

@Component({
  selector: 'app-main-layout',
  imports: [
    RouterOutlet,
    HeaderComponent,
    RrhhSidebarComponent,
    ResponsableSidebarComponent,
  ],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class MainLayoutComponent {
  protected readonly authService = inject(AuthService);
}

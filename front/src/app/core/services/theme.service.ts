import { effect, inject, Injectable, signal } from '@angular/core';

const THEME_KEY = 'corredor_azul_dark_mode';

@Injectable({ providedIn: 'root' })
export class ThemeService {
  private readonly darkMode = signal(this.loadPreference());

  readonly isDarkMode = this.darkMode.asReadonly();

  constructor() {
    effect(() => {
      const isDark = this.darkMode();
      document.documentElement.classList.toggle('dark', isDark);
      localStorage.setItem(THEME_KEY, String(isDark));
    });
  }

  toggle(): void {
    this.darkMode.update((value) => !value);
  }

  setDarkMode(enabled: boolean): void {
    this.darkMode.set(enabled);
  }

  private loadPreference(): boolean {
    const stored = localStorage.getItem(THEME_KEY);
    if (stored !== null) {
      return stored === 'true';
    }

    return window.matchMedia('(prefers-color-scheme: dark)').matches;
  }
}

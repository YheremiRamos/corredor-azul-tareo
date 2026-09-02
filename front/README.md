# Frontend — FATIMA Tareo

Aplicación Angular 21 para el sistema de tareo de asistencia.

## Requisitos

- Node.js 20+
- npm 10+
- Backend corriendo en http://localhost:8080

## Instalación

```powershell
cd front
npm install
```

> **Nota:** Si en tu PC tienes un `C:\Users\PC\.npmrc` global de trabajo (proxy Pichincha), `front/.npmrc` lo anula solo en este proyecto para que `npm install` use `https://registry.npmjs.org/` directo.

## Desarrollo

```powershell
npm start
```

Abre http://localhost:4200

## Build producción

```powershell
npm run build
```

Salida en `dist/`.

## Stack

- Angular 21 (standalone, signals, `rxResource`, zoneless)
- PrimeNG 21 + PrimeIcons
- Tailwind CSS 4
- Arquitectura hexagonal por feature

## Estructura

```
src/app/
  core/           guards, interceptors, layout
  features/
    auth/         login
    dashboard/
    tareo/        matriz de asistencia
    periodos/
    colaboradores/
    seguimiento/
    reportes/
    usuarios/
    areas/
```

## Variables de entorno

- `src/environments/environment.ts` → `apiUrl: http://localhost:8080/api/v1`
- `src/environments/environment.prod.ts` → configurar URL de producción

## Problemas comunes

| Problema | Solución |
|----------|----------|
| `npm install` falla con `ENOTFOUND` | Revisa tu conexión o proxy en `~/.npmrc` global |
| Error CORS | Backend debe estar en puerto 8080 con CORS habilitado |

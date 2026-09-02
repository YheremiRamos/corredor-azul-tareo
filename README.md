# Corredor Azul — Sistema de Tareo y Asistencia

Plataforma web del **Consorcio Transporte Arequipa** para registro, seguimiento y consolidación del tareo de colaboradores.

![Login](docs/screenshots/login-corredor-azul.png)

## Documentación

| Archivo | Contenido |
|---------|-----------|
| **[SETUP.md](SETUP.md)** | Instalación local paso a paso + credenciales + troubleshooting |
| [back/README.md](back/README.md) | API, endpoints, módulos Maven |
| [front/README.md](front/README.md) | Angular, build, npm |
| [back/.env.example](back/.env.example) | Variables Supabase/producción |

## Arranque rápido

```powershell
# Terminal 1
cd back
mvn clean install -DskipTests
mvn spring-boot:run -pl bootstrap "-Dspring-boot.run.profiles=dev"

# Terminal 2
cd front
npm install
npm start
```

→ http://localhost:4200 · Login: `admin` / `admin123`

## Stack

- **Backend:** Java 21, Spring Boot 3, arquitectura hexagonal, JWT, Flyway
- **Frontend:** Angular 21, PrimeNG, Tailwind CSS 4
- **BD dev:** H2 en memoria · **BD prod:** PostgreSQL (Supabase)

## Usuarios seed

| Usuario | Password | Rol |
|---------|----------|-----|
| admin | admin123 | RRHH |
| shirley | admin123 | RRHH |
| responsable01 | admin123 | Responsable |

## Ciclo de tareo

Período **22 → 21** · Quincena 1 (22–07) · Quincena 2 (08–21) · 15 códigos de asistencia (AV = alias Excel VA)

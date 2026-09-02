# Backend — FATIMA Tareo

API REST en Java 21 + Spring Boot 3 con arquitectura hexagonal.

## Requisitos

| Herramienta | Versión |
|-------------|---------|
| Java JDK | 21+ |
| Maven | 3.9+ |

Verifica:

```powershell
java -version
mvn -version
```

Si no tienes Maven, descárgalo de https://maven.apache.org/download.cgi o usa el wrapper si está disponible.

## Arranque rápido (desarrollo)

Perfil `dev` → base H2 en memoria, sin PostgreSQL:

```powershell
cd back
mvn clean install -DskipTests
mvn spring-boot:run -pl bootstrap "-Dspring-boot.run.profiles=dev"
```

- API: http://localhost:8080/api/v1
- Swagger: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console (JDBC: `jdbc:h2:mem:tareo`, user `sa`, sin password)

Flyway aplica automáticamente `V1__schema.sql` y `V2__seed_catalogos.sql`.

## Compilar y tests

```powershell
cd back
mvn clean test
mvn clean package -DskipTests
```

## Producción con Supabase / PostgreSQL

1. Copia `.env.example` a variables de entorno o crea `application-prod.yml`.
2. Configura:

```env
DATABASE_URL=jdbc:postgresql://db.xxxxx.supabase.co:5432/postgres?currentSchema=app
DATABASE_USER=postgres
DATABASE_PASSWORD=tu_password
JWT_SECRET=secreto-de-al-menos-32-caracteres
RRHH_EMAIL=rrhh@ctarequipa.pe
```

3. Arranca con perfil prod:

```powershell
mvn spring-boot:run -pl bootstrap -am -Dspring-boot.run.profiles=prod
```

## Módulos Maven

| Módulo | Responsabilidad |
|--------|-----------------|
| `domain` | Modelos, VOs, servicios de dominio (sin Spring) |
| `application` | Casos de uso y puertos |
| `infrastructure` | REST, JPA, JWT, mail, reportes |
| `bootstrap` | Main, Flyway, configuración |

## Usuarios seed (dev)

| Usuario | Password | Rol |
|---------|----------|-----|
| admin | admin123 | RRHH |
| shirley | admin123 | RRHH |
| responsable01 | admin123 | RESPONSABLE (área OPE) |

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/v1/auth/login` | Login JWT |
| GET | `/api/v1/periodos` | Listar períodos |
| POST | `/api/v1/periodos` | Crear período |
| POST | `/api/v1/tareo/habilitar` | Habilitar tareo + snapshot |
| GET | `/api/v1/tareo/{id}/matriz?quincena=1` | Matriz de asistencia |
| PUT | `/api/v1/tareo/{id}/asistencias` | Guardar celdas |
| POST | `/api/v1/tareo/{id}/culminar?quincena=1` | Enviar quincena |
| GET | `/api/v1/reportes/export?formato=excel` | Exportar reporte |

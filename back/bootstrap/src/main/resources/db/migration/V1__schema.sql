CREATE SCHEMA IF NOT EXISTS app;

CREATE TABLE app.configuracion_tareo (
    id              BIGSERIAL PRIMARY KEY,
    dia_inicio_mes  INT NOT NULL DEFAULT 22,
    dia_fin_mes     INT NOT NULL DEFAULT 21,
    dia_corte_q1    INT NOT NULL DEFAULT 7,
    dia_inicio_q2   INT NOT NULL DEFAULT 8
);

CREATE TABLE app.area (
    id          VARCHAR(10) PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    activo      BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE app.subarea (
    id          VARCHAR(20) PRIMARY KEY,
    area_id     VARCHAR(10) NOT NULL REFERENCES app.area(id),
    nombre      VARCHAR(100) NOT NULL,
    activo      BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE app.tipo_trabajador (
    id      VARCHAR(10) PRIMARY KEY,
    nombre  VARCHAR(50) NOT NULL,
    activo  BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE app.categoria_asistencia (
    codigo      VARCHAR(5) PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    activo      BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE app.turno (
    id      VARCHAR(20) PRIMARY KEY,
    nombre  VARCHAR(50) NOT NULL,
    activo  BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE app.rol (
    id      VARCHAR(20) PRIMARY KEY,
    nombre  VARCHAR(50) NOT NULL
);

CREATE TABLE app.usuario (
    id              BIGSERIAL PRIMARY KEY,
    username        VARCHAR(50) NOT NULL UNIQUE,
    password_hash   VARCHAR(255) NOT NULL,
    nombre          VARCHAR(150) NOT NULL,
    email           VARCHAR(150),
    activo          BOOLEAN NOT NULL DEFAULT TRUE,
    rol_id          VARCHAR(20) NOT NULL REFERENCES app.rol(id)
);

CREATE TABLE app.usuario_area (
    usuario_id  BIGINT NOT NULL REFERENCES app.usuario(id) ON DELETE CASCADE,
    area_id     VARCHAR(10) NOT NULL REFERENCES app.area(id),
    PRIMARY KEY (usuario_id, area_id)
);

CREATE TABLE app.usuario_subarea (
    usuario_id  BIGINT NOT NULL REFERENCES app.usuario(id) ON DELETE CASCADE,
    subarea_id  VARCHAR(20) NOT NULL REFERENCES app.subarea(id),
    PRIMARY KEY (usuario_id, subarea_id)
);

CREATE TABLE app.colaborador (
    id                  BIGSERIAL PRIMARY KEY,
    codigo              VARCHAR(20) NOT NULL UNIQUE,
    dni                 VARCHAR(20) NOT NULL,
    nombres             VARCHAR(200) NOT NULL,
    tipo_trabajador_id  VARCHAR(10) NOT NULL REFERENCES app.tipo_trabajador(id),
    area_id             VARCHAR(10) NOT NULL REFERENCES app.area(id),
    subarea_id          VARCHAR(20) REFERENCES app.subarea(id),
    cargo               VARCHAR(100),
    fecha_ingreso       DATE,
    fecha_cese          DATE,
    activo              BOOLEAN NOT NULL DEFAULT TRUE,
    es_jefatura         BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE app.periodo (
    id              BIGSERIAL PRIMARY KEY,
    anio            INT NOT NULL,
    mes             INT NOT NULL,
    nombre          VARCHAR(50) NOT NULL,
    fecha_inicio    DATE NOT NULL,
    fecha_fin       DATE NOT NULL,
    estado          VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
    UNIQUE (anio, mes)
);

CREATE TABLE app.periodo_dia (
    id              BIGSERIAL PRIMARY KEY,
    periodo_id      BIGINT NOT NULL REFERENCES app.periodo(id) ON DELETE CASCADE,
    fecha           DATE NOT NULL,
    orden           INT NOT NULL,
    quincena        INT NOT NULL CHECK (quincena IN (1, 2)),
    dia_semana      VARCHAR(2) NOT NULL,
    UNIQUE (periodo_id, fecha)
);

CREATE TABLE app.tareo (
    id                  BIGSERIAL PRIMARY KEY,
    periodo_id          BIGINT NOT NULL REFERENCES app.periodo(id),
    area_id             VARCHAR(10) NOT NULL REFERENCES app.area(id),
    subarea_id          VARCHAR(20) REFERENCES app.subarea(id),
    habilitado          BOOLEAN NOT NULL DEFAULT FALSE,
    estado_q1           VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    estado_q2           VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    fecha_envio_q1      TIMESTAMP,
    fecha_envio_q2      TIMESTAMP,
    usuario_envio_q1    VARCHAR(50),
    usuario_envio_q2    VARCHAR(50),
    UNIQUE (periodo_id, area_id, subarea_id)
);

CREATE TABLE app.tareo_colaborador (
    id                  BIGSERIAL PRIMARY KEY,
    tareo_id            BIGINT NOT NULL REFERENCES app.tareo(id) ON DELETE CASCADE,
    colaborador_id      BIGINT NOT NULL,
    codigo_snapshot     VARCHAR(20) NOT NULL,
    dni_snapshot        VARCHAR(20) NOT NULL,
    nombres_snapshot    VARCHAR(200) NOT NULL,
    tipo_snapshot       VARCHAR(10) NOT NULL,
    area_snapshot       VARCHAR(10) NOT NULL,
    subarea_snapshot    VARCHAR(20),
    cargo_snapshot      VARCHAR(100),
    UNIQUE (tareo_id, colaborador_id)
);

CREATE TABLE app.asistencia (
    id                      BIGSERIAL PRIMARY KEY,
    tareo_colaborador_id    BIGINT NOT NULL REFERENCES app.tareo_colaborador(id) ON DELETE CASCADE,
    periodo_dia_id          BIGINT NOT NULL REFERENCES app.periodo_dia(id),
    categoria_codigo        VARCHAR(5) REFERENCES app.categoria_asistencia(codigo),
    turno_id                VARCHAR(20) REFERENCES app.turno(id),
    bonificacion_nocturna   BOOLEAN NOT NULL DEFAULT FALSE,
    he_total                NUMERIC(5,2) DEFAULT 0,
    he_25                   NUMERIC(5,2) DEFAULT 0,
    he_30                   NUMERIC(5,2) DEFAULT 0,
    observacion             TEXT,
    UNIQUE (tareo_colaborador_id, periodo_dia_id)
);

CREATE TABLE app.auditoria_cambio (
    id              BIGSERIAL PRIMARY KEY,
    usuario_id      BIGINT NOT NULL,
    usuario_nombre  VARCHAR(150) NOT NULL,
    fecha_hora      TIMESTAMP NOT NULL DEFAULT NOW(),
    entidad         VARCHAR(50) NOT NULL,
    entidad_id      VARCHAR(50) NOT NULL,
    campo           VARCHAR(50) NOT NULL,
    valor_anterior  VARCHAR(500),
    valor_nuevo     VARCHAR(500),
    motivo          TEXT
);

CREATE INDEX idx_colaborador_area ON app.colaborador(area_id);
CREATE INDEX idx_tareo_periodo ON app.tareo(periodo_id);
CREATE INDEX idx_asistencia_tareo_colab ON app.asistencia(tareo_colaborador_id);

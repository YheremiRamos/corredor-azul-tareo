INSERT INTO app.configuracion_tareo (dia_inicio_mes, dia_fin_mes, dia_corte_q1, dia_inicio_q2)
VALUES (22, 21, 7, 8);

INSERT INTO app.rol (id, nombre) VALUES
('RRHH', 'Recursos Humanos'),
('RESPONSABLE', 'Responsable de Area'),
('DELEGADO', 'Delegado');

INSERT INTO app.area (id, nombre) VALUES
('SIS', 'SISTEMAS'),
('DAT', 'DATOS'),
('LEG', 'LEGAL'),
('RRHH', 'RRHH'),
('OPE', 'OPERACIONES'),
('CONT', 'CONTABILIDAD'),
('LIQ', 'LIQUIDACION'),
('SEG', 'SEGURIDAD'),
('GADM', 'GERENCIA ADMINISTRATIVA'),
('LIM', 'LIMPIEZA'),
('ALM', 'ALMACEN'),
('COM', 'COMUNICACIONES'),
('MAN', 'MANTENIMIENTO'),
('GGEN', 'GERENCIA GENERAL');

INSERT INTO app.subarea (id, area_id, nombre) VALUES
('CCO', 'OPE', 'CENTRO_CONTROL'),
('COND', 'OPE', 'CONDUCTORES_PATIO'),
('GOP', 'OPE', 'GERENCIA_OPERACIONES'),
('DEP_INS', 'OPE', 'DESPACHO_E_INSPECTORIA'),
('GUAD', 'OPE', 'GUADAVIA'),
('INSP', 'LIQ', 'INSPECTORIA'),
('REC', 'LIQ', 'RECAUDO'),
('PRO_LEO', 'LIQ', 'ZONA_PAGA_LEONCIO'),
('PRO_BOL', 'LIQ', 'ZONA_PAGA_BOLIVIA');

INSERT INTO app.tipo_trabajador (id, nombre) VALUES
('PLA', 'PLANILLA'),
('PRAC', 'PRACTICANTE'),
('HONA', 'HONORARIOS');

INSERT INTO app.categoria_asistencia (codigo, descripcion) VALUES
('A', 'Asistencia'),
('F', 'Falta'),
('D', 'Descanso'),
('V', 'Vacaciones'),
('DM', 'Descanso Medico'),
('AM', 'Atencion Medica'),
('LCG', 'Licencia con Goce'),
('LSG', 'Licencia sin Goce'),
('LPP', 'Lic. Paternidad'),
('S', 'Suspension'),
('SP', 'Subsidio'),
('CE', 'Cesado'),
('FT', 'Trabajo en feriado'),
('FD', 'Descanso por feriado'),
('AV', 'Adelanto de vacaciones');

INSERT INTO app.turno (id, nombre) VALUES
('MANANA', 'MANANA'),
('TARDE', 'TARDE'),
('NOCHE', 'NOCHE'),
('PARTIDO', 'PARTIDO');

-- admin / shirley (password: admin123) BCrypt
INSERT INTO app.usuario (username, password_hash, nombre, email, activo, rol_id) VALUES
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Administrador RRHH', 'admin@ctarequipa.pe', TRUE, 'RRHH'),
('shirley', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Shirley', 'shirley@ctarequipa.pe', TRUE, 'RRHH'),
('responsable01', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Responsable Operaciones', 'resp01@ctarequipa.pe', TRUE, 'RESPONSABLE');

INSERT INTO app.usuario_area (usuario_id, area_id)
SELECT u.id, 'RRHH' FROM app.usuario u WHERE u.username = 'shirley';
INSERT INTO app.usuario_area (usuario_id, area_id)
SELECT u.id, 'LIQ' FROM app.usuario u WHERE u.username = 'shirley';
INSERT INTO app.usuario_area (usuario_id, area_id)
SELECT u.id, a.id FROM app.usuario u, app.area a WHERE u.username = 'admin';
INSERT INTO app.usuario_area (usuario_id, area_id)
SELECT u.id, 'OPE' FROM app.usuario u WHERE u.username = 'responsable01';

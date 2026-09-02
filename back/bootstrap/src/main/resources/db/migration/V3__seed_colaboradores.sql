-- Colaboradores de prueba para poder visualizar la matriz de tareo.
-- Area OPE (donde responsable01 tiene acceso) con varias subareas y tipos.
-- fecha_ingreso pasada y sin cese => activos en cualquier periodo reciente.

INSERT INTO app.colaborador
    (codigo, dni, nombres, tipo_trabajador_id, area_id, subarea_id, cargo, fecha_ingreso, fecha_cese, activo, es_jefatura)
VALUES
    -- OPERACIONES · Centro de Control
    ('OPE-001', '45781234', 'Perez Garcia Juan Carlos',      'PLA',  'OPE', 'CCO',     'Operador de control',   DATE '2023-03-01', NULL, TRUE, FALSE),
    ('OPE-002', '40876512', 'Lopez Torres Maria Elena',       'PLA',  'OPE', 'CCO',     'Operadora de control',  DATE '2022-07-15', NULL, TRUE, FALSE),
    ('OPE-003', '71234598', 'Sanchez Flores Pedro Luis',      'PRAC', 'OPE', 'CCO',     'Practicante control',   DATE '2024-01-10', NULL, TRUE, FALSE),
    -- OPERACIONES · Conductores / Patio
    ('OPE-004', '43219876', 'Ramirez Flores Carlos Alberto',  'PLA',  'OPE', 'COND',    'Conductor',             DATE '2021-05-20', NULL, TRUE, FALSE),
    ('OPE-005', '44556677', 'Torres Quispe Luis Miguel',      'PLA',  'OPE', 'COND',    'Conductor',             DATE '2022-02-01', NULL, TRUE, FALSE),
    ('OPE-006', '46778899', 'Quispe Mamani Rosa Maria',       'PLA',  'OPE', 'COND',    'Conductora',            DATE '2023-09-12', NULL, TRUE, FALSE),
    ('OPE-007', '72345611', 'Diaz Rojas Roberto Andres',      'PRAC', 'OPE', 'COND',    'Practicante patio',     DATE '2024-04-01', NULL, TRUE, FALSE),
    -- OPERACIONES · Despacho e Inspectoria
    ('OPE-008', '41235678', 'Huaman Ccahua Elena Beatriz',    'PLA',  'OPE', 'DEP_INS', 'Inspectora',            DATE '2022-11-03', NULL, TRUE, FALSE),
    ('OPE-009', '42346789', 'Vargas Nina Jorge Antonio',      'PLA',  'OPE', 'DEP_INS', 'Despachador',           DATE '2021-08-18', NULL, TRUE, FALSE),
    -- OPERACIONES · Gerencia Operaciones (jefatura)
    ('OPE-010', '09876543', 'Castro Medina Fernando Jose',    'PLA',  'OPE', 'GOP',     'Gerente de Operaciones',DATE '2020-01-15', NULL, TRUE, TRUE),

    -- SISTEMAS (para pruebas de RRHH / admin)
    ('SIS-001', '48123456', 'Mendoza Ríos Ana Lucia',         'PLA',  'SIS', NULL,      'Desarrolladora',        DATE '2022-03-01', NULL, TRUE, FALSE),
    ('SIS-002', '47654321', 'Gutierrez Paredes Diego Martin', 'PLA',  'SIS', NULL,      'Soporte TI',            DATE '2023-06-10', NULL, TRUE, FALSE),
    ('SIS-003', '73456123', 'Ramos Salas Camila Fernanda',    'PRAC', 'SIS', NULL,      'Practicante TI',        DATE '2024-02-15', NULL, TRUE, FALSE),

    -- LIQUIDACION · Recaudo (para pruebas de shirley)
    ('LIQ-001', '45987612', 'Flores Aguilar Miguel Angel',    'PLA',  'LIQ', 'REC',     'Recaudador',            DATE '2022-05-05', NULL, TRUE, FALSE),
    ('LIQ-002', '46123789', 'Chavez Ortiz Patricia Isabel',   'PLA',  'LIQ', 'INSP',    'Inspectora liquidacion',DATE '2023-01-20', NULL, TRUE, FALSE);

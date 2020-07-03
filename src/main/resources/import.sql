

INSERT INTO USUARIO (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('claudio@gmail.com', 'claudio', 'vargas', '162682946', '2020-01-01', '1986-09-02', 'claudio.jpg', true, '$2a$10$FKMfOPra7pd1ayVKhrpXZ.oBzZ59Q1R.pp2tPiVfl.a5O8lqmmQPm');
INSERT INTO USUARIO (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('a@gmail.com', 'a', 'a', '111111111', '2020-01-01', '1986-09-02', 'a.jpg', true, '$2a$10$6tohpuotggPiF9eyFCatu.fQ67Z5PYgX2UMiX/y8T8ZsENEN5ZJ.m');

INSERT INTO ROLE (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (nombre) VALUES ('ROLE_USER');

INSERT INTO usuarios_roles (email, id_role) VALUES ('claudio@gmail.com', 1);
INSERT INTO usuarios_roles (email, id_role) VALUES ('a@gmail.com', 2);



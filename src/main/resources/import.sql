INSERT INTO PERFIL(id_perfil, nombre) VALUES (1, 'administrador');
INSERT INTO PERFIL(id_perfil, nombre) VALUES (2, 'usuario');


INSERT INTO USUARIO (email, nombre, apellido, rut, id_perfil, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('claudio@gmail.com', 'claudio', 'vargas', '162682946', 1, '2020-01-01', '1986-09-02', 'claudio.jpg', true, '$2a$10$v23FfbSKRjvnrZP5peGGFuU1avkHcH2lH1nWqS04fbKtFOcX1LgDa');
INSERT INTO USUARIO (email, nombre, apellido, rut, id_perfil, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('a@gmail.com', 'a', 'a', '111111111', 2, '2020-01-01', '1986-09-02', 'a.jpg', true, '$2a$10$NBJZga1GbGqrs/a3QcrA9.rLldHlAvjT45q2UOFtqc/T6022CnW06');
INSERT INTO USUARIO (email, nombre, apellido, rut, id_perfil, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('b@gmail.com', 'b', 'b', '222222222', 2, '2020-01-01', '1986-09-02', 'b.jpg', true, '$2a$10$LVxNynvxuNtuIBRiNOxjpuzVYb6Ly2mZ1r.YYic.345kEipmgsAQq');

INSERT INTO ROLE (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (nombre) VALUES ('ROLE_USER');

INSERT INTO USER_AUTHORITIES (id_usuario, id_role) VALUES ('claudio@gmail.com', 1);
INSERT INTO USER_AUTHORITIES (id_usuario, id_role) VALUES ('a@gmail.com', 2);
INSERT INTO USER_AUTHORITIES (id_usuario, id_role) VALUES ('b@gmail.com', 2);



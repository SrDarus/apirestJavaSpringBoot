

INSERT INTO USUARIO (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('claudio@gmail.com', 'claudio', 'vargas', '162682946', '2020-01-01', '1986-09-02', 'claudio.jpg', true, '$2a$10$v23FfbSKRjvnrZP5peGGFuU1avkHcH2lH1nWqS04fbKtFOcX1LgDa');
INSERT INTO USUARIO (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('a@gmail.com', 'a', 'a', '111111111', '2020-01-01', '1986-09-02', 'a.jpg', true, '$2a$10$NBJZga1GbGqrs/a3QcrA9.rLldHlAvjT45q2UOFtqc/T6022CnW06');

INSERT INTO ROLE (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (nombre) VALUES ('ROLE_USER');

INSERT INTO USER_AUTHORITIES (email, id_role) VALUES ('claudio@gmail.com', 1);
INSERT INTO USER_AUTHORITIES (email, id_role) VALUES ('a@gmail.com', 2);



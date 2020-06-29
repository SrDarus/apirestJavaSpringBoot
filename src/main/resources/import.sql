

INSERT INTO USUARIO (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('claudio@gmail.com', 'claudio', 'vargas', '162682946', '2020-01-01', '1986-09-02', 'claudio.jpg', true, '$2a$10$TWWH1tbBgSC53FSFTHPUkeEE7zuS3kLc6ak4srUC2E0HvrNF9Z.DO');
INSERT INTO USUARIO (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('a@gmail.com', 'a', 'a', '111111111', '2020-01-01', '1986-09-02', 'a.jpg', true, '$2a$10$IkKRw6hHo2aAXcfDcJl8De2Wy/aPN3nN/vI7SZyO2DhN1kVlOQLtq');

INSERT INTO ROLE (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (nombre) VALUES ('ROLE_USER');

INSERT INTO USER_AUTHORITIES (email, id_role) VALUES ('claudio@gmail.com', 1);
INSERT INTO USER_AUTHORITIES (email, id_role) VALUES ('a@gmail.com', 2);





INSERT INTO USUARIO (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('claudio@gmail.com', 'claudio', 'vargas', '162682946', '2020-01-01', '1986-09-02', 'claudio.jpg', true, '$2a$10$4mJp0OGH46PGbmTXhYt42u.Ok/Jjh8BgdhTKpGWDFYu7Agt30pzoa');
INSERT INTO USUARIO (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('a@gmail.com', 'a', 'a', '111111111', '2020-01-01', '1986-09-02', 'a.jpg', true, '$2a$10$AFX633tcM5AnubkKlzlAOOtopmjG61b9DzbPanZgWm6Qu65wDMxdu');

INSERT INTO ROLE (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (nombre) VALUES ('ROLE_USER');

INSERT INTO usuarios_roles (email, id_role) VALUES ('claudio@gmail.com', 1);
INSERT INTO usuarios_roles (email, id_role) VALUES ('a@gmail.com', 2);



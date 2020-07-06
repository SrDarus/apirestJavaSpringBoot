

INSERT INTO USUARIO (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('claudio@gmail.com', 'claudio', 'vargas', '162682946', '2020-01-01', '1986-09-02', 'claudio.jpg', true, '$2y$12$DledASuo0PPy/Yvas5ULDu0LshEf1GZudwtDQ19cu8QcEsO/vFIgW');
INSERT INTO USUARIO (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('a@gmail.com', 'a', 'a', '111111111', '2020-01-01', '1986-09-02', 'a.jpg', true, '$2y$12$lZ2wELwVy743L1o4VvtnF.kbuRcw6flLNpDzXTs2bej4JUNp6ukl.');

INSERT INTO ROLE (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (nombre) VALUES ('ROLE_USER');

INSERT INTO usuarios_roles (email, id_role) VALUES ('claudio@gmail.com', 1);
INSERT INTO usuarios_roles (email, id_role) VALUES ('a@gmail.com', 2);;



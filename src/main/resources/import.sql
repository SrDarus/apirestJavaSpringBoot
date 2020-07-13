

INSERT INTO usuarios (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('claudio@gmail.com', 'claudio', 'vargas', '162682946', '2020-01-01', '1986-09-02', 'claudio.jpg', true, '$2a$10$FKMfOPra7pd1ayVKhrpXZ.oBzZ59Q1R.pp2tPiVfl.a5O8lqmmQPm');
INSERT INTO usuarios (email, nombre, apellido, rut, fecha_creacion, fecha_nacimiento, foto, enabled, password) VALUES ('a@gmail.com', 'a', 'a', '111111111', '2020-01-01', '1986-09-02', 'a.jpg', true, '$2a$10$6tohpuotggPiF9eyFCatu.fQ67Z5PYgX2UMiX/y8T8ZsENEN5ZJ.m');


INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES ('ROLE_USER');

INSERT INTO usuarios_roles (email, id_role) VALUES ('claudio@gmail.com', 1);
INSERT INTO usuarios_roles (email, id_role) VALUES ('a@gmail.com', 2);


/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, usuario_email, create_at) VALUES('Factura equipos de oficina', null, "claudio@gmail.com", NOW());

INSERT INTO facturas_items (id_factura, id_producto, cantidad) VALUES(1, 1, 1);
INSERT INTO facturas_items (id_factura, id_producto, cantidad) VALUES(1, 2, 2);
INSERT INTO facturas_items (id_factura, id_producto, cantidad) VALUES(1, 4, 2);
INSERT INTO facturas_items (id_factura, id_producto, cantidad) VALUES(1, 5, 2);

INSERT INTO facturas (descripcion, observacion, usuario_email, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', "a@gmail.com", NOW());
INSERT INTO facturas_items (cantidad, id_factura, id_producto) VALUES(3, 2, 6);
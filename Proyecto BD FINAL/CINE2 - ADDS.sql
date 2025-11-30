INSERT INTO Pelicula (id_pelicula, titulo, genero, duracion, clasificacion) 
VALUES (1, 'Avengers Endgame', 'Acción', 181, 'PG-13');

INSERT INTO Pelicula (id_pelicula, titulo, genero, duracion, clasificacion) 
VALUES (2, 'Coco', 'Animación', 105, 'PG');

INSERT INTO Sala (id_sala, numero, capacidad, tipo)
VALUES (1, 1, 150, 'IMAX');

INSERT INTO Sala (id_sala, numero, capacidad, tipo)
VALUES (2, 2, 90, '2D');

INSERT INTO Funcion (id_funcion, id_pelicula, id_sala, fecha, hora, precio)
VALUES (1, 1, 1, TO_DATE('2025-11-20', 'YYYY-MM-DD'), '18:00', 120.00);

INSERT INTO Funcion (id_funcion, id_pelicula, id_sala, fecha, hora, precio)
VALUES (2, 2, 2, TO_DATE('2025-11-21', 'YYYY-MM-DD'), '15:30', 80.00);

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono)
VALUES (1, 'Juan Perez', 'juan@mail.com', '5511223344');

INSERT INTO CLIENTE (id_cliente, nombre, email, telefono)
VALUES (2, 'Ana Lopez', 'ana@mail.com', NULL);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, numero_asiento, precio)
VALUES (1, 1, 1, 10, 120.00);

INSERT INTO BOLETO (id_boleto, id_funcion, id_cliente, numero_asiento, precio)
VALUES (2, 2, 2, 7, 80.00);
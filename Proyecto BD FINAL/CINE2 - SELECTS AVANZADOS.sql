ALTER TABLE Cliente ADD CONSTRAINT unico_email UNIQUE (email);

ALTER TABLE Funcion ADD CONSTRAINT chk_precio CHECK (precio > 0);

SELECT c.nombre, p.titulo
FROM Boleto b
JOIN Cliente c ON b.id_cliente = c.id_cliente
JOIN Funcion f ON b.id_funcion = f.id_funcion
JOIN Pelicula p ON f.id_pelicula = p.id_pelicula;

SELECT titulo
FROM Pelicula
WHERE id_pelicula IN (
    SELECT id_pelicula FROM Funcion WHERE precio > 100
);

SELECT COUNT(*), MAX(precio), MIN(precio)
FROM Funcion;

CREATE INDEX idx_cliente_email ON Cliente(email);
CREATE INDEX idx_pelicula_titulo ON Pelicula(titulo);

BEGIN;

UPDATE Boleto SET precio = 150 WHERE id_boleto = 1;

ROLLBACK;

COMMIT;

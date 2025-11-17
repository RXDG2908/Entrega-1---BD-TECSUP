INSERT INTO Pelicula VALUES (10, 'Titanic', 'Drama', 210, 'PG-13');

UPDATE Pelicula
SET duracion = 195
WHERE id_pelicula = 10;

DELETE FROM Pelicula
WHERE id_pelicula = 10;

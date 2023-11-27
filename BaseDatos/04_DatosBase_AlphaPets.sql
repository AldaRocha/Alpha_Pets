-- ---------------------------------------------------------------------------- --
-- Archivo: 04_DatosBase_AlphaPets.sql												--
-- Version: 1.0                                                     			--
-- Autor:   Francisco Javier Rocha Aldana   									--
-- Email:   rochaaldanafcojavier@gmail.com / 21000459@alumnos.utleon.edu.mx		--
-- Fecha de elaboracion: 14-11-2023                                 			--
-- ---------------------------------------------------------------------------- --

INSERT INTO dispensador(numeroSerie) VALUES("ABC12345");

SELECT * FROM dispensador;

INSERT INTO persona(nombrePersona, idUsuario, idDispensador) VALUES("Francisco Rocha", 1, 1);

SELECT * FROM persona;

INSERT INTO mascota(nombreMascota, edadMascota, razaMascota, tamanioMascota, idPersona) VALUES("Max", 3, "Doberman", "Mediano", 1);

SELECT * FROM v_buscarporusuario;
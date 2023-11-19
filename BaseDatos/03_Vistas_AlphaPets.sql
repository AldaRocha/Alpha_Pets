-- ---------------------------------------------------------------------------- --
-- Archivo: 03_Vistas_AlphaPets.sql												--
-- Version: 1.0                                                     			--
-- Autor:   Francisco Javier Rocha Aldana   									--
-- Email:   rochaaldanafcojavier@gmail.com / 21000459@alumnos.utleon.edu.mx		--
-- Fecha de elaboracion: 14-11-2023                                 			--
-- ---------------------------------------------------------------------------- --

USE alpha_pets;

DROP VIEW IF EXISTS v_buscarPorUsuario;
CREATE VIEW v_buscarPorUsuario AS
	SELECT		u.idUsuario,
				u.nombreUsuario,
				u.contrasenia,
				u.estatus,
                
                p.idPersona,
				p.nombrePersona,

				d.idDispensador,
				d.numeroSerie,
				d.depositoComida,
				d.depositoAgua,
				d.platoComida,
				d.platoAgua,
                
                m.idMascota,
				m.nombreMascota,
				m.edadMascota,
				m.razaMascota,
				m.tamanioMascota
    FROM		usuario u
    INNER JOIN	persona p
    ON			u.idUsuario = p.idUsuario
    INNER JOIN	dispensador d
    ON			p.idDispensador = d.idDispensador
    INNER JOIN	mascota m
    ON			p.idPersona = m.idPersona;
    
DROP VIEW IF EXISTS v_datosDispensador;
CREATE VIEW v_datosDispensador AS
	SELECT	d.depositoComida,
			d.depositoAgua,
			d.platoComida,
			d.platoAgua
    FROM	dispensador d;
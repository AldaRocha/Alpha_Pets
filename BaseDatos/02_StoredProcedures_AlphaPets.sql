-- ---------------------------------------------------------------------------- --
-- Archivo: 02_StoredProcedures_AlphaPets.sql									--
-- Version: 1.0                                                     			--
-- Autor:   Francisco Javier Rocha Aldana   									--
-- Email:   rochaaldanafcojavier@gmail.com / 21000459@alumnos.utleon.edu.mx		--
-- Fecha de elaboracion: 14-11-2023                                 			--
-- ---------------------------------------------------------------------------- --

USE alpha_pets;

DROP PROCEDURE IF EXISTS insertarDispensador;
DELIMITER $$
CREATE PROCEDURE insertarDispensador(
										IN	var_numeroSerie		VARCHAR(45),	-- 1

										OUT	var_idDispensador	INT				-- 2
)
BEGIN
	INSERT INTO dispensador(numeroSerie) VALUES(var_numeroSerie);

	SET	var_idDispensador = LAST_INSERT_ID();
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS crearCuenta;
DELIMITER $$
CREATE PROCEDURE crearCuenta(
								IN	var_nombreUsuario	VARCHAR(30),	-- 1
								IN	var_contrasenia		VARCHAR(30),	-- 2

								IN	var_idDispensador	INT,			-- 3
                                
                                IN	var_nombrePersona	VARCHAR(50),	-- 4
                                
								IN	var_nombreMascota	VARCHAR(50),	-- 5
								IN	var_edadMascota		INT,			-- 6
								IN	var_razaMascota		VARCHAR(45),	-- 7
								IN	var_tamanioMascota	VARCHAR(45),	-- 8

								OUT	var_idUsuario		INT,			-- 9
                                OUT	var_idPersona		INT,			-- 10
                                OUT	var_idMascota		INT				-- 11
)
BEGIN
	INSERT INTO	usuario	(nombreUsuario, contrasenia)
				VALUES	(var_nombreUsuario, var_contrasenia);
                
	SET	var_idUsuario = LAST_INSERT_ID();
    
    INSERT INTO	persona	(nombrePersona,	idUsuario, idDispensador)
				VALUES	(var_nombrePersona, var_idUsuario, var_idDispensador);
                
	SET var_idPersona = LAST_INSERT_ID();
    
    INSERT INTO	mascota	(nombreMascota,	edadMascota, razaMascota, tamanioMascota, idPersona)
				VALUES	(var_nombreMascota, var_edadMascota, var_razaMascota, var_tamanioMascota, var_idPersona);
                
	SET	var_idMascota = LAST_INSERT_ID();
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS actualizarCuenta;
DELIMITER $$
CREATE PROCEDURE actualizarCuenta(
									IN	var_nombreUsuario	VARCHAR(30),	-- 1
									IN	var_contrasenia		VARCHAR(30),	-- 2
                                
									IN	var_nombrePersona	VARCHAR(50),	-- 3
                                
									IN	var_nombreMascota	VARCHAR(50),	-- 4
									IN	var_edadMascota		INT,			-- 5
									IN	var_razaMascota		VARCHAR(45),	-- 6
									IN	var_tamanioMascota	VARCHAR(45),	-- 7

									IN	var_idUsuario		INT,			-- 8
									IN	var_idPersona		INT,			-- 9
									IN	var_idMascota		INT				-- 10
)
BEGIN
	UPDATE	usuario
    SET		nombreUsuario = var_nombreUsuario,
			contrasenia = var_contrasenia
	WHERE	idUsuario = var_idUsuario;
    
    UPDATE	persona
    SET		nombrePersona = var_nombrePersona
    WHERE	idPersona = var_idPersona;
    
    UPDATE	mascota
    SET		nombreMascota = var_nombreMascota,
			edadMascota = var_edadMascota,
			razaMascota = var_razaMascota,
			tamanioMascota = var_tamanioMascota
	WHERE	idMascota = var_idMascota;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS eliminarCuenta;
DELIMITER $$
CREATE PROCEDURE eliminarCuenta(
									IN	var_idUsuario	INT		-- 1
)
BEGIN
	UPDATE	usuario
    SET		estatus = 0
    WHERE	idUsuario = var_idUsuario;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS activarCuenta;
DELIMITER $$
CREATE PROCEDURE activarCuenta(
									IN	var_idUsuario	INT		-- 1
)
BEGIN
	UPDATE	usuario
    SET		estatus = 1
    WHERE	idUsuario = var_idUsuario;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS actualizarDispensador;
DELIMITER $$
CREATE PROCEDURE actualizarDispensador(
											IN	var_numeroSerie		VARCHAR(45),	-- 1

											IN	var_depositoComida	VARCHAR(45),	-- 2
											IN	var_depositoAgua	VARCHAR(45),	-- 3
											IN	var_platoComida		VARCHAR(45),	-- 4
											IN	var_platoAgua		VARCHAR(45)		-- 5
)
BEGIN
	UPDATE	dispensador
    SET		depositoComida = var_depositoComida,
			depositoAgua = var_depositoAgua,
			platoComida = var_platoComida,
			platoAgua = var_platoAgua
	WHERE	numeroSerie LIKE var_numeroSerie;
END $$
DELIMITER ;

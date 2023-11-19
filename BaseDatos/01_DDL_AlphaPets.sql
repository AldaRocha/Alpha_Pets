-- ---------------------------------------------------------------------------- --
-- Archivo: 01_DDL_AlphaPets.sql												--
-- Version: 1.0                                                     			--
-- Autor:   Francisco Javier Rocha Aldana   									--
-- Email:   rochaaldanafcojavier@gmail.com / 21000459@alumnos.utleon.edu.mx		--
-- Fecha de elaboracion: 13-11-2023                                 			--
-- ---------------------------------------------------------------------------- --

DROP DATABASE IF EXISTS alpha_pets;

CREATE DATABASE IF NOT EXISTS alpha_pets;

USE alpha_pets;

CREATE TABLE usuario(
	idUsuario		INT NOT NULL PRIMARY KEY AUTO_INCREMENT,	-- 1
    nombreUsuario	VARCHAR(30) NOT NULL DEFAULT "",			-- 2
    contrasenia		VARCHAR(30) NOT NULL DEFAULT "",			-- 3
    estatus			INT NOT NULL DEFAULT 1						-- 4
);

CREATE TABLE dispensador(
	idDispensador	INT NOT NULL PRIMARY KEY AUTO_INCREMENT,	-- 1
    numeroSerie		VARCHAR(45) NOT NULL DEFAULT "",			-- 2
    depositoComida	VARCHAR(45) NOT NULL DEFAULT "",			-- 3
    depositoAgua	VARCHAR(45) NOT NULL DEFAULT "",			-- 4
    platoComida		VARCHAR(45) NOT NULL DEFAULT "",			-- 5
    platoAgua		VARCHAR(45) NOT NULL DEFAULT ""				-- 6
);

CREATE TABLE persona(
	idPersona		INT NOT NULL PRIMARY KEY AUTO_INCREMENT,	-- 1
    nombrePersona	VARCHAR(50) NOT NULL DEFAULT "",			-- 2
    idUsuario		INT NOT NULL,								-- 3
    idDispensador	INT NOT NULL,								-- 4
    CONSTRAINT persona_idUsuario_fk FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario),
    CONSTRAINT persona_idDispensador_fk FOREIGN KEY (idDispensador) REFERENCES dispensador(idDispensador)
);

CREATE TABLE mascota(
	idMascota		INT NOT NULL PRIMARY KEY AUTO_INCREMENT,	-- 1
    nombreMascota	VARCHAR(50) NOT NULL DEFAULT "",			-- 2
    edadMascota		INT NOT NULL,								-- 3
    razaMascota		VARCHAR(45) NOT NULL,						-- 4
    tamanioMascota	VARCHAR(45) NOT NULL,						-- 5
    idPersona		INT NOT NULL,								-- 6
    CONSTRAINT mascota_idPersona_fk FOREIGN KEY (idPersona) REFERENCES persona(idPersona)
);

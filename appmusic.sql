-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 16-10-2023 a las 21:38:34
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `appmusic`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artistas`
--

DROP TABLE IF EXISTS `artistas`;
CREATE TABLE IF NOT EXISTS `artistas` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `nombreArtistico` varchar(30) NOT NULL,
  `email` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `password` varchar(60) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `IdRol` int NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_Rolartista` (`IdRol`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `artistas`
--

INSERT INTO `artistas` (`Id`, `nombre`, `nombreArtistico`, `email`, `password`, `IdRol`) VALUES
(1, 'Michael Joseph Jackson', 'Michael Jackson', 'admin123@gmail.com', '$2a$10$zRPqhpoM2BWEfVKmO0yPEekzEIzIxJ86aLGuhnb6q7rkvRdk5vsye', 2),
(6, 'Benito martinez', 'Bad Bunny', 'badbuuny2023@hotmail.com', '$2a$10$zRPqhpoM2BWEfVKmO0yPEekzEIzIxJ86aLGuhnb6q7rkvRdk5vsye', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `canciones`
--

DROP TABLE IF EXISTS `canciones`;
CREATE TABLE IF NOT EXISTS `canciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `genero` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `duracion` time NOT NULL,
  `fechaPublicacion` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `canciones`
--

INSERT INTO `canciones` (`id`, `titulo`, `genero`, `duracion`, `fechaPublicacion`) VALUES
(5, 'Canción 5', 'R&B', '00:03:10', '2023-10-06 22:57:14'),
(6, 'Billie Jean', 'Pop', '00:04:45', '2023-10-09 10:11:05'),
(7, 'Thriller', 'Pop', '00:05:58', '2023-10-09 10:11:05'),
(10, 'Black or White', 'Pop', '00:04:15', '2023-10-09 10:11:05'),
(11, 'Bad', 'Pop', '00:04:07', '2023-10-09 10:11:05'),
(13, 'Don\'t Stop \'Til You Get Enough', 'Disco', '00:06:05', '2023-10-09 10:11:05'),
(14, 'Man in the Mirror', 'Pop', '00:05:19', '2023-10-09 10:11:56'),
(15, 'Heal the World', 'Pop', '00:06:25', '2023-10-09 10:11:56'),
(25, 'Lala', 'urbano latino', '00:03:18', '2023-10-09 15:24:50'),
(27, 'Soy peor', 'urbano latino', '00:03:11', '2023-10-14 12:02:18');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `canciones_artistas`
--

DROP TABLE IF EXISTS `canciones_artistas`;
CREATE TABLE IF NOT EXISTS `canciones_artistas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idCancion` int NOT NULL,
  `idArtista` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_idCancion` (`idCancion`),
  KEY `FK_idArtista` (`idArtista`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `canciones_artistas`
--

INSERT INTO `canciones_artistas` (`id`, `idCancion`, `idArtista`) VALUES
(20, 5, 1),
(21, 6, 1),
(22, 7, 1),
(23, 10, 1),
(24, 11, 1),
(25, 13, 1),
(26, 14, 1),
(27, 15, 1),
(28, 27, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `canciones_favoritas_oyentes`
--

DROP TABLE IF EXISTS `canciones_favoritas_oyentes`;
CREATE TABLE IF NOT EXISTS `canciones_favoritas_oyentes` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `idOyente` int NOT NULL,
  `idCancion` int NOT NULL,
  `fechaAgregada` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_idOyente` (`idOyente`),
  KEY `FK_idcancionf` (`idCancion`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `canciones_favoritas_oyentes`
--

INSERT INTO `canciones_favoritas_oyentes` (`Id`, `idOyente`, `idCancion`, `fechaAgregada`) VALUES
(6, 2, 6, '2023-10-14 09:00:56');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `oyentes`
--

DROP TABLE IF EXISTS `oyentes`;
CREATE TABLE IF NOT EXISTS `oyentes` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `email` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `password` varchar(60) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `idRol` int NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_Roloyente` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `oyentes`
--

INSERT INTO `oyentes` (`Id`, `nombre`, `email`, `password`, `idRol`) VALUES
(2, 'mateo', 'mateo204r@gmail.com', '$2a$10$zRPqhpoM2BWEfVKmO0yPEekzEIzIxJ86aLGuhnb6q7rkvRdk5vsye', 1),
(3, 'oyente3', 'oyente3@gmail.com', '$2a$10$ODiUxiJJYN4/2QL9Zka3cutoDdDn9WdgLbYWsflL378o7dNPFfQUe', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`Id`, `nombre`) VALUES
(1, 'OYENTE'),
(2, 'ARTISTA');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `artistas`
--
ALTER TABLE `artistas`
  ADD CONSTRAINT `FK_Rolartista` FOREIGN KEY (`IdRol`) REFERENCES `roles` (`Id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Filtros para la tabla `canciones_artistas`
--
ALTER TABLE `canciones_artistas`
  ADD CONSTRAINT `FK_idArtista` FOREIGN KEY (`idArtista`) REFERENCES `artistas` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idCancion` FOREIGN KEY (`idCancion`) REFERENCES `canciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `canciones_favoritas_oyentes`
--
ALTER TABLE `canciones_favoritas_oyentes`
  ADD CONSTRAINT `FK_idcancionf` FOREIGN KEY (`idCancion`) REFERENCES `canciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idOyente` FOREIGN KEY (`idOyente`) REFERENCES `oyentes` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `oyentes`
--
ALTER TABLE `oyentes`
  ADD CONSTRAINT `FK_Roloyente` FOREIGN KEY (`idRol`) REFERENCES `roles` (`Id`) ON DELETE RESTRICT ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

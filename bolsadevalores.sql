-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 01-07-2020 a las 19:58:34
-- Versión del servidor: 5.7.26
-- Versión de PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de datos: `bolsadevalores`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `companies`
--

CREATE TABLE `companies` (
  `companyRFC` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `stockNumber` int(11) UNSIGNED NOT NULL,
  `stockValue` double UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `companies`
--

INSERT INTO `companies` (`companyRFC`, `stockNumber`, `stockValue`) VALUES
('GB2020', 33, 700),
('GC2022', 96, 1200),
('GP2021', 65, 2501),
('ZG', 80, 1000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transactions`
--

CREATE TABLE `transactions` (
  `userRFC` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `companyRFC` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operatedStocks` int(11) NOT NULL,
  `operatedStocksPrice` double UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `transactions`
--

INSERT INTO `transactions` (`userRFC`, `companyRFC`, `date`, `operatedStocks`, `operatedStocksPrice`) VALUES
('HB2194', 'GB2020', '2020-06-30 06:19:44', 20, 2001),
('HB2194', 'GB2020', '2020-06-30 18:23:31', 2, 2020),
('HB2194', 'GP2021', '2020-06-30 18:30:02', 2, 552),
('HB2194', 'GB2020', '2020-06-30 18:33:04', 4, 2020),
('HB2194', 'GB2020', '2020-06-30 18:37:41', 5, 5000),
('HB2194', 'GB2020', '2020-06-30 18:41:14', 6, 6060),
('HB2194', 'GB2020', '2020-06-30 18:45:49', 3, 300),
('HB2194', 'GB2020', '2020-06-30 18:53:03', 20, 300),
('HB2194', 'GB2020', '2020-06-30 18:57:53', 5, 400),
('HB2194', 'GB2020', '2020-06-30 19:02:07', 5, 400),
('HB2194', 'GB2020', '2020-06-30 19:05:57', 2, 500),
('HB2194', 'GB2020', '2020-06-30 19:09:02', 2, 560),
('HB2194', 'GB2020', '2020-06-30 19:15:17', 2, 400),
('HB2194', 'GB2020', '2020-06-30 19:18:16', 10, 701),
('HB2194', 'GB2020', '2020-06-30 19:59:03', 1, 1000),
('HB2194', 'GB2020', '2020-06-30 20:44:21', 18, 5000),
('HB2194', 'ZG', '2020-06-30 21:31:13', 99, 350),
('MR5678', 'ZG', '2020-07-01 01:52:32', 1, 700),
('MR5678', 'GP2021', '2020-07-01 02:17:56', 10, 1100),
('MR5678', 'GB2020', '2020-07-01 02:23:39', 10, 1100),
('HB2194', 'ZG', '2020-07-01 02:29:29', 9, 800),
('HB2194', 'GB2020', '2020-07-01 02:37:54', -10, 900),
('JD9873', 'GP2021', '2020-07-01 02:45:46', 30, 3000),
('MR5678', 'GB2020', '2020-07-01 02:56:30', -10, 850),
('JD9873', 'ZG', '2020-07-01 02:56:30', 10, 1000),
('JD9873', 'GB2020', '2020-07-01 02:57:54', -10, 700),
('MR5678', 'GP2021', '2020-07-01 03:09:45', -5, 2600),
('JD9873', 'GP2021', '2020-07-01 03:09:45', 5, 2500),
('JD9873', 'GP2021', '2020-07-01 03:10:47', -5, 2500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `userRFC` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `stockNumber` int(11) UNSIGNED NOT NULL,
  `lastBuyPrice` double UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`userRFC`, `name`, `stockNumber`, `lastBuyPrice`) VALUES
('HB2194', 'Hector Burgos', 187, 900),
('JD9873', 'Juan Duran', 30, 2500),
('MR5678', 'Mauricio Rodriguez', 6, 2600);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`companyRFC`);

--
-- Indices de la tabla `transactions`
--
ALTER TABLE `transactions`
  ADD KEY `transactions_ibfk_2` (`companyRFC`),
  ADD KEY `transactions_ibfk_1` (`userRFC`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD UNIQUE KEY `userRFC` (`userRFC`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`userRFC`) REFERENCES `users` (`userRFC`) ON DELETE CASCADE,
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`companyRFC`) REFERENCES `companies` (`companyRFC`) ON DELETE CASCADE;

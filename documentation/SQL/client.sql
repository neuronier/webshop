-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Hoszt: 127.0.0.1
-- Létrehozás ideje: 2015. Aug 13. 15:04
-- Szerver verzió: 5.6.17
-- PHP verzió: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Adatbázis: `test`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientId` bigint(20) DEFAULT NULL,
  `clientName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `billingAddress_id` bigint(20) DEFAULT NULL,
  `deliveryAddress_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7877DFEB6633B967` (`billingAddress_id`),
  KEY `FK7877DFEB67C9EFA0` (`deliveryAddress_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK7877DFEB67C9EFA0` FOREIGN KEY (`deliveryAddress_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FK7877DFEB6633B967` FOREIGN KEY (`billingAddress_id`) REFERENCES `address` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

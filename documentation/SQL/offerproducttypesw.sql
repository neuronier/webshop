-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Hoszt: 127.0.0.1
-- Létrehozás ideje: 2015. Aug 13. 18:40
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
-- Tábla szerkezet ehhez a táblához `offerproducttypesw`
--

CREATE TABLE IF NOT EXISTS `offerproducttypesw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `offer_id` bigint(20) DEFAULT NULL,
  `productType_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24F0443186A1F02C` (`offer_id`),
  KEY `FK24F04431E638E7AC` (`productType_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `offerproducttypesw`
--
ALTER TABLE `offerproducttypesw`
  ADD CONSTRAINT `FK24F04431E638E7AC` FOREIGN KEY (`productType_id`) REFERENCES `producttype` (`id`),
  ADD CONSTRAINT `FK24F0443186A1F02C` FOREIGN KEY (`offer_id`) REFERENCES `offer` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

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
-- Tábla szerkezet ehhez a táblához `offer`
--

CREATE TABLE IF NOT EXISTS `offer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentOffer_id` bigint(20) DEFAULT NULL,
  `parentOfferGroup_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4892A3C936AED6` (`parentOffer_id`),
  KEY `FK4892A3CD99D119E` (`parentOfferGroup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `offer`
--
ALTER TABLE `offer`
  ADD CONSTRAINT `FK4892A3CD99D119E` FOREIGN KEY (`parentOfferGroup_id`) REFERENCES `offergroup` (`id`),
  ADD CONSTRAINT `FK4892A3C936AED6` FOREIGN KEY (`parentOffer_id`) REFERENCES `offer` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

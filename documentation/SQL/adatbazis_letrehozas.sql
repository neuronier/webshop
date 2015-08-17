-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Hoszt: 127.0.0.1
-- Létrehozás ideje: 2015. Aug 17. 07:14
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
-- Tábla szerkezet ehhez a táblához `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `house` varchar(255) DEFAULT NULL,
  `postcode` bigint(20) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `bugreport`
--

CREATE TABLE IF NOT EXISTS `bugreport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `problem` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `productType_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8DE138A8540D6C28` (`client_id`),
  KEY `FK8DE138A8E638E7AC` (`productType_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientId` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `billingAddress_id` bigint(20) DEFAULT NULL,
  `deliveryAddress_id` bigint(20) DEFAULT NULL,
  `shoppingCart_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7877DFEB6633B967` (`billingAddress_id`),
  KEY `FK7877DFEB67C9EFA0` (`deliveryAddress_id`),
  KEY `FK7877DFEB4AC6D8A8` (`shoppingCart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `client_role_sw`
--

CREATE TABLE IF NOT EXISTS `client_role_sw` (
  `Client_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKE355365950D45C21` (`roles_id`),
  KEY `FKE3553659540D6C28` (`Client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `offer`
--

CREATE TABLE IF NOT EXISTS `offer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentOfferGroup_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4892A3CD99D119E` (`parentOfferGroup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `offergroup`
--

CREATE TABLE IF NOT EXISTS `offergroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentOfferGroup_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7F21E1A3D99D119E` (`parentOfferGroup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `producttype`
--

CREATE TABLE IF NOT EXISTS `producttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `itemNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `purchase`
--

CREATE TABLE IF NOT EXISTS `purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `fullCost` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6BC36921540D6C28` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `purchasedoffersw`
--

CREATE TABLE IF NOT EXISTS `purchasedoffersw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quanty` int(11) NOT NULL,
  `offer_id` bigint(20) DEFAULT NULL,
  `purchase_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC1F9D4FD86A1F02C` (`offer_id`),
  KEY `FKC1F9D4FDE844FCE8` (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `shoppingcart`
--

CREATE TABLE IF NOT EXISTS `shoppingcart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `shoppingcart_offer_sw`
--

CREATE TABLE IF NOT EXISTS `shoppingcart_offer_sw` (
  `ShoppingCart_id` bigint(20) NOT NULL,
  `offers_id` bigint(20) NOT NULL,
  KEY `FK3881BCFE28664B11` (`offers_id`),
  KEY `FK3881BCFE4AC6D8A8` (`ShoppingCart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user_role_sw`
--

CREATE TABLE IF NOT EXISTS `user_role_sw` (
  `User_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKAD012E5950D45C21` (`roles_id`),
  KEY `FKAD012E5957E6A7A8` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `bugreport`
--
ALTER TABLE `bugreport`
  ADD CONSTRAINT `FK8DE138A8E638E7AC` FOREIGN KEY (`productType_id`) REFERENCES `producttype` (`id`),
  ADD CONSTRAINT `FK8DE138A8540D6C28` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Megkötések a táblához `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK7877DFEB4AC6D8A8` FOREIGN KEY (`shoppingCart_id`) REFERENCES `shoppingcart` (`id`),
  ADD CONSTRAINT `FK7877DFEB6633B967` FOREIGN KEY (`billingAddress_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FK7877DFEB67C9EFA0` FOREIGN KEY (`deliveryAddress_id`) REFERENCES `address` (`id`);

--
-- Megkötések a táblához `client_role_sw`
--
ALTER TABLE `client_role_sw`
  ADD CONSTRAINT `FKE3553659540D6C28` FOREIGN KEY (`Client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKE355365950D45C21` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`);

--
-- Megkötések a táblához `offer`
--
ALTER TABLE `offer`
  ADD CONSTRAINT `FK4892A3CD99D119E` FOREIGN KEY (`parentOfferGroup_id`) REFERENCES `offergroup` (`id`);

--
-- Megkötések a táblához `offergroup`
--
ALTER TABLE `offergroup`
  ADD CONSTRAINT `FK7F21E1A3D99D119E` FOREIGN KEY (`parentOfferGroup_id`) REFERENCES `offergroup` (`id`);

--
-- Megkötések a táblához `offerproducttypesw`
--
ALTER TABLE `offerproducttypesw`
  ADD CONSTRAINT `FK24F04431E638E7AC` FOREIGN KEY (`productType_id`) REFERENCES `producttype` (`id`),
  ADD CONSTRAINT `FK24F0443186A1F02C` FOREIGN KEY (`offer_id`) REFERENCES `offer` (`id`);

--
-- Megkötések a táblához `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `FK6BC36921540D6C28` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Megkötések a táblához `purchasedoffersw`
--
ALTER TABLE `purchasedoffersw`
  ADD CONSTRAINT `FKC1F9D4FDE844FCE8` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`),
  ADD CONSTRAINT `FKC1F9D4FD86A1F02C` FOREIGN KEY (`offer_id`) REFERENCES `offer` (`id`);

--
-- Megkötések a táblához `shoppingcart_offer_sw`
--
ALTER TABLE `shoppingcart_offer_sw`
  ADD CONSTRAINT `FK3881BCFE4AC6D8A8` FOREIGN KEY (`ShoppingCart_id`) REFERENCES `shoppingcart` (`id`),
  ADD CONSTRAINT `FK3881BCFE28664B11` FOREIGN KEY (`offers_id`) REFERENCES `offer` (`id`);

--
-- Megkötések a táblához `user_role_sw`
--
ALTER TABLE `user_role_sw`
  ADD CONSTRAINT `FKAD012E5957E6A7A8` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKAD012E5950D45C21` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

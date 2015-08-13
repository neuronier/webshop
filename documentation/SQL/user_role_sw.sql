-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Hoszt: 127.0.0.1
-- Létrehozás ideje: 2015. Aug 13. 18:42
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
-- Megkötések a táblához `user_role_sw`
--
ALTER TABLE `user_role_sw`
  ADD CONSTRAINT `FKAD012E5957E6A7A8` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKAD012E5950D45C21` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 10 fév. 2019 à 23:43
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `servermobile`
--
CREATE DATABASE IF NOT EXISTS `servermobile` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `servermobile`;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `idEvent` varchar(255) NOT NULL,
  `ACTIVE` tinyint(1) DEFAULT '0',
  `DATE` longblob,
  `TITLE` varchar(255) DEFAULT NULL,
  `USER_idUser` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEvent`),
  KEY `FK_EVENT_USER_idUser` (`USER_idUser`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `expense`
--

DROP TABLE IF EXISTS `expense`;
CREATE TABLE IF NOT EXISTS `expense` (
  `idExpense` varchar(255) NOT NULL,
  `AMOUNT` double DEFAULT NULL,
  `WORDING` varchar(255) DEFAULT NULL,
  `EVENT_idEvent` varchar(255) DEFAULT NULL,
  `USER_idUser` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idExpense`),
  KEY `FK_EXPENSE_EVENT_idEvent` (`EVENT_idEvent`),
  KEY `FK_EXPENSE_USER_idUser` (`USER_idUser`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PSEUDO` varchar(255) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  UNIQUE KEY `PSEUDO` (`PSEUDO`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user_event`
--

DROP TABLE IF EXISTS `user_event`;
CREATE TABLE IF NOT EXISTS `user_event` (
  `user_id` varchar(255) NOT NULL,
  `event_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`event_id`),
  KEY `FK_user_event_event_id` (`event_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

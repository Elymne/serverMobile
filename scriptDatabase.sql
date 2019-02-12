CREATE USER 'servermobile'@'%' IDENTIFIED WITH mysql_native_password AS '***';
GRANT USAGE ON *.* TO 'servermobile'@'%' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
CREATE DATABASE IF NOT EXISTS `servermobile`;GRANT ALL PRIVILEGES ON `servermobile`.* TO 'servermobile'@'%';

-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 12 fév. 2019 à 10:38
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

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id_event` varchar(255) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_event`),
  KEY `FKcmum0fi7ofop556b5j0qq4oif` (`user_id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `expense`
--

DROP TABLE IF EXISTS `expense`;
CREATE TABLE IF NOT EXISTS `expense` (
  `id` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `wording` varchar(255) DEFAULT NULL,
  `event_id_event` varchar(255) DEFAULT NULL,
  `user_id_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4quqq2acim150ndj3h3t0qf3q` (`event_id_event`),
  KEY `FKetoo90fxw5g8u5gpwhpqhor0` (`user_id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pseudo` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_it5d8tethuijmhllwd27doaqv` (`pseudo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user_event`
--

DROP TABLE IF EXISTS `user_event`;
CREATE TABLE IF NOT EXISTS `user_event` (
  `user_id` varchar(255) NOT NULL,
  `event_id` varchar(255) NOT NULL,
  KEY `FKspe8srtv69gubpphvrnd7wekt` (`event_id`),
  KEY `FKk3smcqwou8absq8qjt3wk4vy9` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `FKcmum0fi7ofop556b5j0qq4oif` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `expense`
--
ALTER TABLE `expense`
  ADD CONSTRAINT `FK4quqq2acim150ndj3h3t0qf3q` FOREIGN KEY (`event_id_event`) REFERENCES `event` (`id_event`),
  ADD CONSTRAINT `FKetoo90fxw5g8u5gpwhpqhor0` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `user_event`
--
ALTER TABLE `user_event`
  ADD CONSTRAINT `FKk3smcqwou8absq8qjt3wk4vy9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `FKspe8srtv69gubpphvrnd7wekt` FOREIGN KEY (`event_id`) REFERENCES `event` (`id_event`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

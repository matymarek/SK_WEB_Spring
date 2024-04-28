-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1:3306
-- Vytvořeno: Ned 14. dub 2024, 09:13
-- Verze serveru: 5.7.36
-- Verze PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `sk_web`
--

DROP DATABASE IF EXISTS sk_web;
CREATE DATABASE sk_web;
USE sk_web;

-- Create application user
DROP USER IF EXISTS 'dev'@'localhost';
CREATE USER 'dev'@'localhost' IDENTIFIED BY 'password'; -- CHANGE PASSWORD IN PRODUCTION
GRANT INSERT, UPDATE, DELETE, SELECT, EXECUTE ON sk_web.* TO 'dev'@'localhost';
FLUSH PRIVILEGES;

--
-- Struktura tabulky `imgs`
--

DROP TABLE IF EXISTS `imgs`;
CREATE TABLE IF NOT EXISTS `imgs` (
                                      `img_id` int(11) NOT NULL AUTO_INCREMENT,
                                      `img_name` varchar(255) COLLATE utf8mb4_czech_ci NOT NULL,
                                      `alt` varchar(128) COLLATE utf8mb4_czech_ci NOT NULL,
                                      PRIMARY KEY (`img_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

--
-- Vypisuji data pro tabulku `imgs`
--

INSERT INTO `imgs` (`img_id`, `img_name`, `alt`) VALUES
                                                     (1, '278816255_1596898900665533_6943992846064985525_n.jpg', 'Závody Trutnov'),
                                                     (2, '278885485_1596898803998876_4862730857743477219_n.jpg', 'Závody Trutnov'),
                                                     (3, '278904439_1596899370665486_8659626751212633772_n.jpg', 'Závody Trutnov');

-- --------------------------------------------------------

--
-- Struktura tabulky `posts`
--

DROP TABLE IF EXISTS `posts`;
CREATE TABLE IF NOT EXISTS `posts` (
                                       `post_id` int(11) NOT NULL AUTO_INCREMENT,
                                       `title` varchar(255) COLLATE utf8mb4_czech_ci NOT NULL,
                                       `content` text COLLATE utf8mb4_czech_ci NOT NULL,
                                       `user_id` int(11) NOT NULL,
                                       PRIMARY KEY (`post_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

--
-- Vypisuji data pro tabulku `posts`
--

INSERT INTO `posts` (`post_id`, `title`, `content`, `user_id`) VALUES
    (1, 'Začátek sezóny', 'Zdravím, zima už nám skončila a sezóna za chvíli začíná. Prosím všechny o zaslání prohlídek od sportovního lékaře na rok 2024. První letošní závody se budou konat 6.-7.4. v Trutnově a v Českém Vrbném. Nezapomeňte se včas přihlásit na nástěnce na loděnici.\r\n\r\nMatyáš Marek', 1),
    (2, 'Přihlášky na víkend 6.-7.4.', 'Pěkné slunečné odpoledne všem, ti co mají platnou prohlídku a chtějí závodit, deadline přihlášek je do 3.4. do 20.00. Pokud jste se nestihli napsat na papír, napište mi e-mail a já vás dohlásím\r\n\r\nMatyáš Marek', 1),
    (3, 'Závody v Trutnově ', 'Závody v Trutnově letos proběhli za velmi slunečného počasí, kdo neměl s sebou opalovací krém to odnesl. Vzhledem k paralelně se konajícím závodům v Českém Vrbném nebyla velká účast, cca 100 startů, a tak bylo doježděno oba dny kolem 15.00 a zbylo spoustu času na užitií si pěkného počasí. Děkuji všem zúčastněným, výsledky jsme měli moc pěkné. Ahoj na dalších závodech\r\n\r\nMatyáš Marek', 1);

-- --------------------------------------------------------

--
-- Struktura tabulky `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
                                       `user_id` int(11) NOT NULL AUTO_INCREMENT,
                                       `login` varchar(128) COLLATE utf8mb4_czech_ci NOT NULL,
                                       `pass` varchar(128) COLLATE utf8mb4_czech_ci NOT NULL,
                                       `admin` boolean NOT NULL,
                                       PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

--
-- Vypisuji data pro tabulku `users`
--

INSERT INTO `users` (`user_id`, `login`, `pass`, `admin`) VALUES
    (1, 'administrator', '$2a$10$OB9R7/JVU5g26oRIhz5LNOy1E5DNmgnwss4T10l7cQFwVGi/sEXRC', true),
    (2, 'commonuser', '$2a$10$xVne6SLSR3xH00j.Lk8HFeZT9Adwu29nBbOFLILV3cZvgEHk/CMTm', false);

--
-- Struktura tabulky `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
                                          `comment_id` int(11) NOT NULL AUTO_INCREMENT,
                                          `text` varchar(255) COLLATE utf8mb4_czech_ci NOT NULL,
                                          `post_id` int(11) NOT NULL,
                                          `user_id` int(11) NOT NULL,
                                          PRIMARY KEY (`comment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_czech_ci;

--
-- Vypisuji data pro tabulku `comments`
--

INSERT INTO `comments` (`comment_id`, `text`, `post_id`, user_id) VALUES
    (1, 'Obratem zašlu', 2, 1);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

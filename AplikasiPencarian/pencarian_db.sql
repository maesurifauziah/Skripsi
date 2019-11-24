-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Nov 2019 pada 11.53
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pencarian_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_user`
--

CREATE TABLE `tabel_user` (
  `npm` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `tlp` varchar(16) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `catatan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tabel_user`
--

INSERT INTO `tabel_user` (`npm`, `nama`, `email`, `tlp`, `username`, `password`, `catatan`) VALUES
('C1A150028', 'Maesuri Fauziah', 'maesurifauziah3@gmail.com', '089663410463', 'maesurifauziah', 'mae7897', ''),
('12345', 'admin', 'admin', '11111', 'admin', 'admin', ''),
('cui', 'cui', 'cui', '11111', 'cui', 'cui', 'saya cui'),
('C1A151111', 'IF UNIBBA', 'ifunibba@gmail.com', '1234567', 'ifunibba', 'ifunibba', 'Teknik informatika UNIBBA '),
('C1A150010', 'Hani Widia', 'haniwidia.23@gmail.com', '082123456789', 'haniwidia', 'hani1234', 'Hai, Saya Hani Widia'),
('11111', 'mae', '111', '111', 'mae', 'mae', 'wwwwww'),
('C1A150010', 'Hani Widia', 'haniwidia23@gmail.com', '1234567890', 'haniwidia', 'hani123', 'Teknik Informatika');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

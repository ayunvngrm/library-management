-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Nov 2023 pada 22.50
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_management`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `book`
--

CREATE TABLE `book` (
  `id` int(5) NOT NULL,
  `title` varchar(50) NOT NULL,
  `author` varchar(30) NOT NULL,
  `isbn` varchar(30) NOT NULL,
  `genre` varchar(15) NOT NULL,
  `stock` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `book`
--

INSERT INTO `book` (`id`, `title`, `author`, `isbn`, `genre`, `stock`) VALUES
(3, 'Pemrograman Dasar Java', 'Sidiq Pratama', '7654356987654', 'Technology', 14),
(4, 'Bulan', 'Tere Liye', '7649864267854', 'Novel', 20),
(5, 'Antah Berantah', 'Sullivan Merguro', '8729765378263', 'Novel', 9),
(6, 'Cahaya Bintang', 'Rangga Prasetya', '8765439725728', 'Novel', 1),
(9, 'Anugerah Baru', 'Gilang Rasyid', '8765468765987', 'Agama', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `book_loan`
--

CREATE TABLE `book_loan` (
  `idLoan` int(11) NOT NULL,
  `title` int(5) NOT NULL,
  `borrower` int(5) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'On Loan'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `book_loan`
--

INSERT INTO `book_loan` (`idLoan`, `title`, `borrower`, `startDate`, `endDate`, `status`) VALUES
(1, 5, 1, '2023-11-01', '2023-11-08', 'Returned'),
(2, 4, 1, '2023-11-05', '2023-11-23', 'Returned'),
(4, 6, 4, '2023-11-05', '2023-11-12', 'Returned'),
(5, 3, 1, '2023-10-10', '2023-11-02', 'On Loan'),
(6, 5, 4, '2023-11-06', '2023-11-13', 'On Loan'),
(7, 6, 1, '2023-11-06', '2023-11-13', 'Returned');

-- --------------------------------------------------------

--
-- Struktur dari tabel `member`
--

CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(70) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `member`
--

INSERT INTO `member` (`id`, `name`, `address`, `phoneNumber`, `email`, `status`) VALUES
(1, 'Ayu Novianingrum Romadani', 'Jalan Buah Batu No 302', '082245796755', 'ayuromadani53@gmail.com', 'Aktif'),
(4, 'Ardyansyah Hardianto', 'Jalan Kenanga', '086543987567', 'ardy@gmail.com', 'Aktif');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `book_loan`
--
ALTER TABLE `book_loan`
  ADD PRIMARY KEY (`idLoan`),
  ADD KEY `borrower` (`borrower`),
  ADD KEY `title` (`title`);

--
-- Indeks untuk tabel `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `book`
--
ALTER TABLE `book`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `book_loan`
--
ALTER TABLE `book_loan`
  MODIFY `idLoan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `member`
--
ALTER TABLE `member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `book_loan`
--
ALTER TABLE `book_loan`
  ADD CONSTRAINT `book_loan_ibfk_1` FOREIGN KEY (`borrower`) REFERENCES `member` (`id`),
  ADD CONSTRAINT `book_loan_ibfk_2` FOREIGN KEY (`title`) REFERENCES `book` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

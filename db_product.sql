-- phpMyAdmin SQL Dump
-- version 6.0.0-dev+20251015.833796a1df
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 16, 2025 at 04:55 PM
-- Server version: 8.4.3
-- PHP Version: 8.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_product`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `harga` double NOT NULL,
  `stok` int NOT NULL,
  `kategori` varchar(255) NOT NULL,
  `kualitas` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `nama`, `harga`, `stok`, `kategori`, `kualitas`) VALUES
('P001', 'Laptop Asus', 8500000, 15, 'Elektronik', 4),
('P002', 'Mouse Logitech', 350000, 23, 'Elektronik', 5),
('P003', 'Keyboard Mechanical', 750000, 41, 'Elektronik', 4),
('P004', 'Roti Tawar', 15000, 16, 'Makanan', 3),
('P005', 'Susu UHT', 12000, 12, 'Minuman', 2),
('P006', 'Kemeja Putih', 125000, 25, 'Pakaian', 1),
('P007', 'Celana Jeans', 200000, 33, 'Pakaian', 4),
('P008', 'Pensil 2B', 3000, 54, 'Alat Tulis', 3),
('P009', 'Buku Tulis', 8000, 14, 'Alat Tulis', 4),
('P010', 'Air Mineral', 5000, 22, 'Minuman', 5),
('P011', 'Smartphone Samsung', 4500000, 29, 'Elektronik', 5),
('P012', 'Kue Brownies', 25000, 20, 'Makanan', 4),
('P013', 'Jaket Hoodie', 180000, 70, 'Pakaian', 5),
('P014', 'Pulpen Gel', 5000, 20, 'Alat Tulis', 2),
('P015', 'Teh Botol', 8000, 15, 'Minuman', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

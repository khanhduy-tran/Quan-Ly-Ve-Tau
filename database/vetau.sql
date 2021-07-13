-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 14, 2020 lúc 08:10 AM
-- Phiên bản máy phục vụ: 10.4.10-MariaDB
-- Phiên bản PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dbvetau`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vetau`
--

CREATE TABLE `vetau` (
  `MaGhe` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaKH` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TenTau` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoToa` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `GaDi` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `GaDen` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ThanhToan` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `vetau`
--

INSERT INTO `vetau` (`MaGhe`, `MaKH`, `TenTau`, `SoToa`, `GaDi`, `GaDen`, `ThanhToan`) VALUES
('G1', 'KH1', 'SE7', 'T6', 'TP.HCM', 'Đà Nẵng', '850000'),
('G2', 'KH2', 'SE7', 'T6', 'Quảng Ngãi', 'Đà Nẵng', '120000'),
('G3', 'KH3', 'SE7', 'T8', 'Quảng Ngãi', 'Đà Nẵng', '120000'),
('G4', 'KH4', 'SE8', 'T8', 'Quảng Ngãi', 'Đà Nẵng', '120000'),
('G5', 'KH5', 'SE8', 'T8', 'Quảng Ngãi', 'Đà Nẵng', '120000');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `vetau`
--
ALTER TABLE `vetau`
  ADD PRIMARY KEY (`MaGhe`),
  ADD KEY `vetau_ibfk_1` (`MaKH`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `vetau`
--
ALTER TABLE `vetau`
  ADD CONSTRAINT `vetau_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

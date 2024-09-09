-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 27, 2024 lúc 05:02 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `tb_36_37`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `created_at`, `email`, `password`, `updated_at`, `username`, `role`) VALUES
(4, '2024-05-07 23:51:41.000000', '123@gmail.com', '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk02', 'ROLE_ADMIN'),
(5, '2024-05-07 23:51:41.000000', 'eee@gmail.com', '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk03', 'ROLE_USER'),
(6, '2024-05-07 23:51:41.000000', NULL, '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk04', 'ROLE_USER'),
(7, '2024-05-07 23:51:41.000000', NULL, '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk05', 'ROLE_USER'),
(8, '2024-05-07 23:51:41.000000', NULL, '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk06', 'ROLE_USER'),
(9, '2024-05-07 23:51:41.000000', NULL, '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk07', 'ROLE_USER'),
(10, '2024-05-07 23:51:41.000000', NULL, '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk08', 'ROLE_USER'),
(11, '2024-05-07 23:51:41.000000', NULL, '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk09', 'ROLE_USER'),
(12, '2024-05-07 23:51:41.000000', NULL, '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk001', 'ROLE_USER'),
(13, '2024-05-07 23:51:41.000000', NULL, '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk002', 'ROLE_USER'),
(14, '2024-05-07 23:51:41.000000', NULL, '$2a$12$tc8Bhw1h4o.Cnji0N/IKmeSVaHxEers5JKTi3AYucPSiIhBt9n1V2', '2024-05-07 23:51:56.000000', 'kk003', 'ROLE_USER'),
(15, NULL, 'havanbinh22022003@gmail.com', '1234', NULL, 'admin1', 'ROLE_ADMIN'),
(16, NULL, 'binhha123@gmail.com', '12345', NULL, 'binhha123', 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Trái cây'),
(2, 'Đồ dùng sinh hoạt');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `post`
--

INSERT INTO `post` (`id`, `image`, `author`, `content`, `title`, `date`) VALUES
(1, 'thac si.jpg', 'Hà Văn Bình', 'eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee', '1 bài báo', '2024-05-21'),
(2, 'dd.png', 'dương quang châu', 'và đó là 1 con vịttttttttttttttttttttttttttttttttttttttttttttttttt', 'tin hot', '2024-05-21'),
(3, 'bbb.jpg', 'lâm văn hoàng', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'siêu nóng hổi', '2024-05-21');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `image`, `price`, `productname`, `category_id`) VALUES
(21, 'ahrri3.png', 123455, '23213', 1),
(22, 'giải pháp.png', 5555, '21323', 2),
(23, 'khe uoc yone.png', 22, 'w', 1),
(24, 'win k tinhanh.png', 4234, 'gao', NULL),
(25, 'ahrri3.png', 2321312, 'siêu nhân', NULL),
(26, 'cart.jpg', 2, 'bọ ', NULL),
(27, 'cart.jpg', 2, 'bọ ', NULL),
(28, 'cart.jpg', 2, 'bọ ', NULL),
(29, 'win k tinhanh.png', 4234, 'gao', NULL),
(30, 'win k tinhanh.png', 4234, 'gao', NULL),
(31, 'cart.jpg', 2, 'bọ ', NULL),
(32, 'tulen.jpg', 12, 'aaaaaaaaab', NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

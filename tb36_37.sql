-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for tb_36_37
CREATE DATABASE IF NOT EXISTS `tb_36_37` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `tb_36_37`;

-- Dumping structure for table tb_36_37.account
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('ROLE_ADMIN','ROLE_USER') NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table tb_36_37.account: ~4 rows (approximately)
INSERT INTO `account` (`account_id`, `created_at`, `email`, `password`, `role`, `updated_at`, `username`) VALUES
	(1, '2025-05-06 04:14:50.000000', 'havanbinh22022003@gmail.com', '$2a$12$PzbIT7GqjgeJqzHuDd4tTup3sMZFIitMWHfeHiPFBg/TqpC8wZ.Oq', 'ROLE_ADMIN', '2025-05-06 04:15:32.000000', 'kk02'),
	(2, NULL, 'havanbinh2003@gmail.com', '$2a$10$Pv6zfqQQ4bTS.rPubRGfZeSywqV8t.5qLDLm0C5YnT9UMyeBGH9Zu', 'ROLE_USER', NULL, 'admin1'),
	(3, '2025-05-06 22:42:38.000000', 'test123@gmail.com', '$2a$12$BfPjsQ.z6HCCo0h80eQSMOly6G9H3vdWWoabElu/hJbj.npMntM92', 'ROLE_ADMIN', '2025-05-06 21:48:31.000000', 'admin2'),
	(4, NULL, 'diephuyen123@gmail.com', '$2a$10$yxMRN/m0R7dYfs.7Pw4.rue1iCpmPohLlp39fhyWYKh1sNbmCyOSi', 'ROLE_USER', NULL, 'diephuyen');

-- Dumping structure for table tb_36_37.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table tb_36_37.category: ~2 rows (approximately)
INSERT INTO `category` (`id`, `categoryname`) VALUES
	(1, 'trái cây'),
	(2, 'hoa quả');

-- Dumping structure for table tb_36_37.orderdetails
CREATE TABLE IF NOT EXISTS `orderdetails` (
  `orderdetailid` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `orderid` varchar(12) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`orderdetailid`),
  KEY `FKj4gc2ja2otvwemf4rho2lp3s8` (`orderid`),
  KEY `FKdhs1mfl2idhy7idq8i2e3ftgb` (`product_id`),
  CONSTRAINT `FKdhs1mfl2idhy7idq8i2e3ftgb` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKj4gc2ja2otvwemf4rho2lp3s8` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table tb_36_37.orderdetails: ~1 rows (approximately)
INSERT INTO `orderdetails` (`orderdetailid`, `price`, `quantity`, `orderid`, `product_id`) VALUES
	(1, NULL, NULL, '', 24);

-- Dumping structure for table tb_36_37.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `orderid` varchar(12) NOT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `orderdate` datetime(6) DEFAULT NULL,
  `receiveaddress` varchar(100) DEFAULT NULL,
  `receivename` varchar(100) DEFAULT NULL,
  `receivephone` varchar(100) DEFAULT NULL,
  `status` enum('ORDER_CANCEL','ORDER_DELIVERING','ORDER_NEW','ORDER_RECEVIED') NOT NULL,
  `account_id` int(11) NOT NULL,
  PRIMARY KEY (`orderid`),
  KEY `FK3c7gbsfawn58r27cf5b2km72f` (`account_id`),
  CONSTRAINT `FK3c7gbsfawn58r27cf5b2km72f` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table tb_36_37.orders: ~1 rows (approximately)
INSERT INTO `orders` (`orderid`, `note`, `orderdate`, `receiveaddress`, `receivename`, `receivephone`, `status`, `account_id`) VALUES
	('', NULL, '2025-05-06 17:05:07.000000', NULL, NULL, NULL, 'ORDER_CANCEL', 2);

-- Dumping structure for table tb_36_37.post
CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table tb_36_37.post: ~3 rows (approximately)
INSERT INTO `post` (`id`, `image`, `author`, `content`, `title`, `date`) VALUES
	(1, 'blog-2.jpg', 'Hà Văn Bình', 'Ở đây chúng tôi cung cấp những sản phẩm nông sản tươi sạch đến tận tay người tiêu dùng', 'thực phẩm tươi sạch', '2024-05-21'),
	(2, 'blog-3.jpg', 'dương quang châu', 'Sản phẩm sạch của chúng tôi luôn đảm bảo chất lượng', 'tin hot', '2024-05-21'),
	(3, 'product-6.jpg', 'lâm văn hoàng', 'Luôn mang đến sự tin tưởng cho người tiêu dùng', 'Trái cây tươi ngon', '2024-05-21');

-- Dumping structure for table tb_36_37.product
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table tb_36_37.product: ~8 rows (approximately)
INSERT INTO `product` (`product_id`, `image`, `price`, `productname`, `category_id`) VALUES
	(21, 'quả đào.jpg', 123455, 'Quả đào', 1),
	(22, 'ca-rot-154235899798180572311-crop-1542359010644581734138.webp', 5555, 'Cà rốt', 1),
	(23, 'product-6.jpg', 22, 'Cà chua', 1),
	(24, 'product-2.jpg', 4234, 'Quả dứa', 1),
	(25, 'product-7.jpg', 2321312, 'Khoai tây', 1),
	(26, 'product-3.jpg', 2, 'Ớt', 1),
	(27, 'product-8.jpg', 2, 'Quả chuối', 1),
	(28, 'product-5.jpg', 2, 'Dưa chuột', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

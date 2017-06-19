# noinspection SqlNoDataSourceInspectionForFile

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `product_description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `user_profile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(25) COLLATE utf8_unicode_ci NOT NULL UNIQUE,
  `first_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(25) COLLATE utf8_unicode_ci,
  PRIMARY KEY (`user_profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;

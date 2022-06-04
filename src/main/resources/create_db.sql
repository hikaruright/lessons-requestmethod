-- DATABASEごと作り直す場合
DROP DATABASE IF EXISTS `chat`;
CREATE DATABASE `chat` 

-- テーブルの生成
USE `chat`;

DROP TABLE IF EXISTS `chatlog`;

CREATE TABLE `chatlog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `message` varchar(1000) NOT NULL,
  `posted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cashback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `day_of_week` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `value` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `item_sale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cashback_percentage` decimal(19,2) DEFAULT NULL,
  `cashback_value` decimal(19,2) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `sale_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgd7wfxfwsxri0wmjcu9429dag` (`product_id`),
  KEY `FK5vekrnttbp2bdxmtnfvvnw9oi` (`sale_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `sale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `total_cashback` decimal(19,2) DEFAULT NULL,
  `total_value` decimal(19,2) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjw88ojfoqquyd9f1obip1ar0g` (`customer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SUNDAY', 'POP', 25.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('MONDAY', 'POP', 7.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('TUESDAY', 'POP', 6.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('WEDNESDAY', 'POP', 2.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('THURSDAY', 'POP', 10.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('FRIDAY', 'POP', 15.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SATURDAY', 'POP', 20.00);

INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SUNDAY', 'MPB', 30.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('MONDAY', 'MPB', 5.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('TUESDAY', 'MPB', 10.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('WEDNESDAY', 'MPB', 15.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('THURSDAY', 'MPB', 20.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('FRIDAY', 'MPB', 25.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SATURDAY', 'MPB', 30.00);

INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SUNDAY', 'CLASSIC', 35.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('MONDAY', 'CLASSIC', 3.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('TUESDAY', 'CLASSIC', 5.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('WEDNESDAY', 'CLASSIC', 8.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('THURSDAY', 'CLASSIC', 13.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('FRIDAY', 'CLASSIC', 18.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SATURDAY', 'CLASSIC', 25.00);

INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SUNDAY', 'ROCK', 40.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('MONDAY', 'ROCK', 10.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('TUESDAY', 'ROCK', 15.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('WEDNESDAY', 'ROCK', 15.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('THURSDAY', 'ROCK', 15.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('FRIDAY', 'ROCK', 20.00);
INSERT INTO `cashback` (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SATURDAY', 'ROCK', 40.00);

INSERT INTO `customer` (`birth_date`, `email`, `name`) VALUES ('1985/08/22', 'reginaldolribeiro@gmail.com', 'Reginaldo Luiz Ribeiro Filho');
INSERT INTO `customer` (`birth_date`, `email`, `name`) VALUES ('1965/10/20', 'fulanodasilva@gmail.com', 'Fulano da Silva');
INSERT INTO `customer` (`birth_date`, `email`, `name`) VALUES ('1970/01/10', 'beltranosouza@gmail.com', 'Beltrano Souza');

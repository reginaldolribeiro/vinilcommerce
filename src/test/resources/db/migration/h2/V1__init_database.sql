CREATE TABLE `cashback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `day_of_week` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `value` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) CHARSET=utf8;

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

INSERT INTO `customer` (`email`, `name`) VALUES ('reginaldolribeiro@gmail.com', 'Reginaldo Luiz Ribeiro Filho');
INSERT INTO `customer` (`email`, `name`) VALUES ('fulanodasilva@gmail.com', 'Fulano da Silva');
INSERT INTO `customer` (`email`, `name`) VALUES ('beltranosouza@gmail.com', 'Beltrano Souza');

INSERT INTO `product` (`artist_name`, `genre`, `name`, `price`) VALUES  ('Jack Stauber','POP','Pop Food',38.03);
INSERT INTO `product` (`artist_name`, `genre`, `name`, `price`) VALUES  ('Pop Smoke','POP','Meet The Woo',43.68);
INSERT INTO `product` (`artist_name`, `genre`, `name`, `price`) VALUES  ('Nana Caymmi','MPB','Brasil MPB',46.78);
INSERT INTO `product` (`artist_name`, `genre`, `name`, `price`) VALUES  ('Chico Buarque','MPB','MPB no JT',21.98);
INSERT INTO `product` (`artist_name`, `genre`, `name`, `price`) VALUES  ('Video Game Players','CLASSIC','40 Classic Video Game Songs',58.17);
INSERT INTO `product` (`artist_name`, `genre`, `name`, `price`) VALUES  ('MKTO','CLASSIC','Classic',41.01);
INSERT INTO `product` (`artist_name`, `genre`, `name`, `price`) VALUES  ('Kid Rock','ROCK','Devil Without A Cause',61.93);
INSERT INTO `product` (`artist_name`, `genre`, `name`, `price`) VALUES  ('PnB Rock','ROCK','Fendi (feat. Nicki Minaj & Murda Beatz)',61.10);
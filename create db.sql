CREATE DATABASE `chickensaledb` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `chickensaledb`.`administration` (
  `login` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `chickensaledb`.`status` (
  `statusId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`statusId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
CREATE TABLE `chickensaledb`.`product` (
  `productId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` float DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `photo` varchar(50) NOT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
CREATE TABLE `chickensaledb`.`user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  `name` varchar(50) NOT NULL,
  `EDRPOU` varchar(12) DEFAULT NULL,
  `deliveryAdress` varchar(50) DEFAULT NULL,
  `tel1` varchar(15) DEFAULT NULL,
  `tel2` varchar(15) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `rating` long DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
create table `chickensaledb`.`actionType`(
  `actiontypeid` int NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`actiontypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `chickensaledb`.`action` (
  `actionId` int(11) NOT NULL AUTO_INCREMENT,
  `complete` boolean DEFAULT NULL,
  `planingEndDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `actiontypeid` int(11)DEFAULT NULL,
  PRIMARY KEY (`actionId`),
  FOREIGN KEY (`userId`) REFERENCES `chickensaledb`.`user` (`userId`),
  FOREIGN KEY (`actiontypeid`) REFERENCES `chickensaledb`.`actionType` (`actiontypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
CREATE TABLE `chickensaledb`.`commission` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `date` date NOT NULL,
  `deliveryAdress` varchar(50) DEFAULT NULL,
  `statusId` int(11) NOT NULL,
  PRIMARY KEY (`orderId`),
  FOREIGN KEY (`userId`) REFERENCES `chickensaledb`.`user` (`userId`),
  FOREIGN KEY (`statusId`) REFERENCES `chickensaledb`.`status` (`statusId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
CREATE TABLE `chickensaledb`.`orderproduct` (
  `productId` int(11) NOT NULL,
  `orderId` int(11) NOT NULL,
  `price` float DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`productId`,`orderId`),
  FOREIGN KEY (`productId`) REFERENCES `chickensaledb`.`product` (`productId`),
  FOREIGN KEY (`orderId`) REFERENCES `chickensaledb`.`commission` (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

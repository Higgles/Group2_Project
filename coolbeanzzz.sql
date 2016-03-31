Drop SCHEMA IF EXISTS `CoolBeanzzz`;
CREATE SCHEMA IF NOT EXISTS `CoolBeanzzz` DEFAULT CHARACTER SET utf8 ;
USE `CoolBeanzzz` ;
 
 -- -----------------------------------------------------
-- Table `CoolBeanzzz`.users
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `CoolBeanzzz`.Users (
id INT(11) NOT NULL AUTO_INCREMENT,
username varchar(50),
passkey varchar(50), 
userType ENUM('SupEng', 'SysAd', 'CSR', 'NetManEng'),
PRIMARY KEY (id)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
 
insert into CoolBeanzzz.Users (username,passkey,userType) values("admin","admin", "SysAd");
insert into CoolBeanzzz.Users (username,passkey,userType) values("supeng","supeng", "SupEng");
insert into CoolBeanzzz.Users (username,passkey,userType) values("csr","csr", "CSR");
insert into CoolBeanzzz.Users (username,passkey,userType) values("netman","netman", "NetManEng");

CREATE TABLE  `CoolBeanzzz`.`roles_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(80) DEFAULT NULL,
  `role_name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into CoolBeanzzz.roles_permissions(permission, role_name) values('SysAd:access', 'SysAd');
insert into CoolBeanzzz.roles_permissions(permission, role_name) values('SupEng:access', 'SupEng');
insert into CoolBeanzzz.roles_permissions(permission, role_name) values('NetManEng:access', 'NetManEng');
insert into CoolBeanzzz.roles_permissions(permission, role_name) values('CSR:access', 'CSR');

-- -----------------------------------------------------
-- Table `CoolBeanzzz`.`Failure Class`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoolBeanzzz`.`Failure Class` (
  `Failure Class` INT(11) NOT NULL COMMENT '',
  `Description` VARCHAR(65) NOT NULL COMMENT '',
  PRIMARY KEY (`Failure Class`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
 
 
-- -----------------------------------------------------
-- Table `CoolBeanzzz`.`UE Table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoolBeanzzz`.`UE Table` (
  `TAC` INT(11) NOT NULL COMMENT '',
  `Marketing Name` VARCHAR(60) NOT NULL COMMENT '',
  `Manufacturer` VARCHAR(45) NOT NULL COMMENT '',
  `Access Capability` VARCHAR(150) NOT NULL COMMENT '',
  `Model` VARCHAR(60) NOT NULL COMMENT '',
  `Vendor Name` VARCHAR(45) NOT NULL COMMENT '',
  `UE Type` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `OS` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `Input Mode` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`TAC`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
 
 
-- -----------------------------------------------------
-- Table `CoolBeanzzz`.`MCC-MNC`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoolBeanzzz`.`MCC-MNC` (
  `MCC` INT(11) NOT NULL DEFAULT '0' COMMENT '',
  `MNC` INT(11) NOT NULL DEFAULT '0' COMMENT '',
  `Country` VARCHAR(45) NOT NULL COMMENT '',
  `Operator` VARCHAR(85) NOT NULL COMMENT '',
  PRIMARY KEY (`MCC`, `MNC`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
 
 
-- -----------------------------------------------------
-- Table `CoolBeanzzz`.`Event Cause`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoolBeanzzz`.`Event Cause` (
  `Cause Code` INT(11) NOT NULL COMMENT '',
  `Event Id` INT(11) NOT NULL COMMENT '',
  `Description` VARCHAR(120) NOT NULL COMMENT '',
  PRIMARY KEY (`Event Id`, `Cause Code`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
 
 
-- -----------------------------------------------------
-- Table `CoolBeanzzz`.`Base Data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoolBeanzzz`.`Base Data` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `Date/Time` DATETIME NOT NULL COMMENT '',
  `Event Id` INT(11) NOT NULL COMMENT '',
  `Failure Class` INT(11) DEFAULT NULL COMMENT '',
  `UE Type` INT(11) NOT NULL COMMENT '',
  `Market` INT(11) NOT NULL COMMENT '',
  `Operator` INT(11) NOT NULL COMMENT '',
  `Cell Id` VARCHAR(40) NOT NULL COMMENT '',
  `Duration` VARCHAR(40) NOT NULL COMMENT '',
  `Cause Code` INT(11) NULL COMMENT '',
  `NE Version` VARCHAR(50) NOT NULL COMMENT '',
  `IMSI` VARCHAR(50) NOT NULL COMMENT '',
  `HIER3_ID` VARCHAR(50) NOT NULL COMMENT '',
  `HIER32_ID` VARCHAR(50) NOT NULL COMMENT '',
  `HIER321_ID` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`Id`)  COMMENT '',
  INDEX `fk_Base Data_UE Table` (`UE Type` ASC)  COMMENT '',
  INDEX `fk_Base Data_Failure Class` (`Failure Class` ASC)  COMMENT '',
  INDEX `fk_Base Data_MCC-MNC_idx` (`Market` ASC, `Operator` ASC)  COMMENT '',
  INDEX `fk_Base Data_Event Cause_idx` (`Event Id` ASC, `Cause Code` ASC)  COMMENT '',
    FOREIGN KEY (`Failure Class`)
    REFERENCES `Failure Class`(`Failure Class`),
   FOREIGN KEY (`UE Type`)
   REFERENCES `UE Table`(`TAC`),
    FOREIGN KEY (`Market` , `Operator`)
    REFERENCES `MCC-MNC` (`MCC` , `MNC`),
    FOREIGN KEY (`Event Id` , `Cause Code`)
    REFERENCES `Event Cause` (`Event Id` , `Cause Code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
 
-- -----------------------------------------------------
-- Table `CoolBeanzzz`.`Erroneous Data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoolBeanzzz`.`Erroneous Data` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `Date/Time` DATETIME NULL COMMENT '',
  `Event Id` INT(11) NULL COMMENT '',
  `Failure Class` VARCHAR(40) NULL COMMENT '',
  `UE Type` INT(11) NULL COMMENT '',
  `Market` INT(11) NULL COMMENT '',
  `Operator` INT(11) NULL COMMENT '',
  `Cell Id` VARCHAR(40) NULL COMMENT '',
  `Duration` VARCHAR(40) NULL COMMENT '',
  `Cause Code` VARCHAR(40) NULL COMMENT '',
  `NE Version` VARCHAR(50) NULL COMMENT '',
  `IMSI` VARCHAR(50) NULL COMMENT '',
  `HIER3_ID` VARCHAR(50) NULL COMMENT '',
  `HIER32_ID` VARCHAR(50) NULL COMMENT '',
  `HIER321_ID` VARCHAR(50) NULL COMMENT '',
  PRIMARY KEY (`Id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
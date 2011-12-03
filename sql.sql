SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `rbsdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `rbsdb` ;

-- -----------------------------------------------------
-- Table `rbsdb`.`User`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rbsdb`.`User` (
  `uid` VARCHAR(60) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `group` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `phone` VARCHAR(45) NULL ,
  PRIMARY KEY (`uid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rbsdb`.`Application`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rbsdb`.`Application` (
  `appid` VARCHAR(60) NOT NULL ,
  `roomid` VARCHAR(45) NOT NULL ,
  `datebegin` DATE NOT NULL ,
  `dateend` DATE NOT NULL ,
  `week` VARCHAR(10) NOT NULL ,
  `timebegin` TIME NOT NULL ,
  `timeend` TIME NOT NULL ,
  `uid` VARCHAR(60) NOT NULL ,
  `status` INT NOT NULL DEFAULT 0 ,
  `applytime` DATETIME NULL ,
  PRIMARY KEY (`appid`) ,
  INDEX `uid` (`uid` ASC) ,
  CONSTRAINT `uid`
    FOREIGN KEY (`uid` )
    REFERENCES `rbsdb`.`User` (`uid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rbsdb`.`Room`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rbsdb`.`Room` (
  `roomid` VARCHAR(60) NOT NULL ,
  `capacity` INT NULL ,
  `facilities` VARCHAR(100) NULL ,
  PRIMARY KEY (`roomid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rbsdb`.`RoomInfo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `rbsdb`.`RoomInfo` (
  `roominfoid` VARCHAR(60) NOT NULL ,
  `roomid` VARCHAR(60) NOT NULL ,
  `datebegin` DATE NOT NULL ,
  `dateend` DATE NOT NULL ,
  `week` VARCHAR(10) NOT NULL ,
  `timebegin` TIME NOT NULL ,
  `timeend` TIME NOT NULL ,
  PRIMARY KEY (`roominfoid`) ,
  INDEX `roomid` (`roomid` ASC) ,
  CONSTRAINT `roomid`
    FOREIGN KEY (`roomid` )
    REFERENCES `rbsdb`.`Room` (`roomid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

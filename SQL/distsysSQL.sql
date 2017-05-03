DROP DATABASE if exists dist_sys;
CREATE DATABASE dist_sys;
USE dist_sys;
CREATE TABLE `dist_sys`.`Track` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100),
    `artist` VARCHAR(100),
    `album` VARCHAR(100),
    `trackId` VARCHAR(100),
    PRIMARY KEY (`id`)
);
INSERT INTO Track ( `name`, `artist`, `album`, `trackId` )
    VALUES ( "test name", "test artist", "test album","test trackId" );

select * from Track;
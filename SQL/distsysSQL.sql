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

CREATE TABLE `dist_sys`.`PlayList` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `playListName` VARCHAR(100),
    `ppid` VARCHAR(100),
    `playListId` VARCHAR(100),
    `playListTrackId` VARCHAR(100),
    PRIMARY KEY (`id`)
);

INSERT INTO Track ( `name`, `artist`, `album`, `trackId` )
    VALUES ( "test name", "test artist", "test album","test trackId" );

select * from Track;

INSERT INTO PlayList ( `playListName`, `ppid`, `playListId`, `playListTrackId` )
    VALUES ( "test name", "test PPID", "test ID","test PlayList trackId" );

select * from PlayList;
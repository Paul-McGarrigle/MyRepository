DROP DATABASE if exists dist_sys;
CREATE DATABASE dist_sys;
USE dist_sys;

CREATE TABLE `dist_sys`.`track` (
	`t_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100),
    `artist` VARCHAR(100),
    `album` VARCHAR(100),
    `trackId` VARCHAR(100),
    PRIMARY KEY (`t_id`)
);

CREATE TABLE `dist_sys`.`playlist` (
	`p_id` INT NOT NULL AUTO_INCREMENT,
    `playListName` VARCHAR(100),
    `ppid` VARCHAR(100),
    `playListId` VARCHAR(100),
    PRIMARY KEY (`p_id`)
	-- CONSTRAINT `uc_ppid` UNIQUE (ppid),
    -- CONSTRAINT `fk_playlist_users` FOREIGN KEY (ppid) REFERENCES users(ppid)
    -- CONSTRAINT `fk_playlist_users` FOREIGN KEY (p_id) REFERENCES users(id)
);

CREATE TABLE `dist_sys`.`playlist_tracks` (
    `playListId` INT,
    `trackId` INT,
    PRIMARY KEY (`playListId`,`trackId`),
	CONSTRAINT `fk_track` FOREIGN KEY (trackId) REFERENCES Track(t_id),
    CONSTRAINT `fk_playlist` FOREIGN KEY (playListId) REFERENCES playlist(p_id)
);

CREATE TABLE `dist_sys`.`users` (
	`id` INT AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `ppid` VARCHAR(100),
    PRIMARY KEY (`id`)
    -- CONSTRAINT `fk_playlist_users` FOREIGN KEY (id) REFERENCES playlist(p_id)
    -- CONSTRAINT `uc_ppid` UNIQUE (ppid)
);

-- INSERT INTO users ( `username`, `password`) VALUES ( "testuser", "testpassword" );
    
select * from Track;
select * from PlayList;
select count(*) from playlist_tracks;
select * from playlist_tracks;
select * from users;
-- use group_project_ericsson;


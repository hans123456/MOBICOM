DROP DATABASE IF EXISTS plan8;
CREATE DATABASE IF NOT EXISTS plan8;
USE plan8;

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `groups` (`id`, `name`, `description`) VALUES
     (1,'admin','Administrator'),
     (2,'members','General User');

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(15) NOT NULL,
  `unique_id` varchar(13) NOT NULL UNIQUE,
  `pic` varchar(10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `activation_code` varchar(40) DEFAULT NULL,
  `forgotten_password_code` varchar(40) DEFAULT NULL,
  `forgotten_password_time` int(11) unsigned DEFAULT NULL,
  `remember_code` varchar(40) DEFAULT NULL,
  `created_on` int(11) unsigned NOT NULL,
  `last_login` int(11) unsigned DEFAULT NULL,
  `active` tinyint(1) unsigned DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users`
    (`id`, `ip_address`, `unique_id`, `username`, `password`, `salt`, `email`, `activation_code`, `forgotten_password_code`, `created_on`, `last_login`, `active`, `first_name`, `last_name`) VALUES
    ('1','127.0.0.1','ABECUAC3','administrator','$2a$07$SeBknntpZror9uyftVopmu61qg0ms8Qv1yV6FG.kQOSM.9QhmTo36','','admin@admin.com','',NULL,'1268889823','1268889823','1', 'Admin','istrator'),
    ('2','127.0.0.1','ABECUAC3Z','abcdefghi','$2a$07$SeBknntpZror9uyftVopmu61qg0ms8Qv1yV6FG.kQOSM.9QhmTo36','','user@user.com','',NULL,'1268889823','1268889823','1', 'user','user'),
    ('3','127.0.0.1','ABECUAC3c','abcd','$2a$07$SeBknntpZror9uyftVopmu61qg0ms8Qv1yV6FG.kQOSM.9QhmTo36','','user1@user1.com','',NULL,'1268889823','1268889823','1', 'user1','user'),
    ('4','127.0.0.1','ABECUAC3k','abcd','$2a$07$SeBknntpZror9uyftVopmu61qg0ms8Qv1yV6FG.kQOSM.9QhmTo36','','user1@user1.com','',NULL,'1268889823','1268889823','1', 'user2','user');

DROP TABLE IF EXISTS `users_groups`;

CREATE TABLE `users_groups` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `group_id` mediumint(8) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_groups_users1_idx` (`user_id`),
  KEY `fk_users_groups_groups1_idx` (`group_id`),
  CONSTRAINT `uc_users_groups` UNIQUE (`user_id`, `group_id`),
  CONSTRAINT `fk_users_groups_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_groups_groups1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users_groups` (`id`, `user_id`, `group_id`) VALUES
     (1,1,1),
     (2,1,2);

DROP TABLE IF EXISTS `login_attempts`;

CREATE TABLE `login_attempts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(15) NOT NULL,
  `login` varchar(100) NOT NULL,
  `time` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `friend_request`;

-- 0 request, 1 friends
CREATE TABLE `friends` (
  `user_a_id` int(11) unsigned NOT NULL,
  `user_b_id` int(11) unsigned NOT NULL,
  `status` int(1) unsigned NOT NULL,
  PRIMARY KEY (`user_a_id`, `user_b_id`),
  KEY `fk_user_a_idx` (`user_a_id`),
  KEY `fk_user_b_idx` (`user_b_id`),
  CONSTRAINT `fk_user_a_idy` FOREIGN KEY (`user_a_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_b_idy` FOREIGN KEY (`user_b_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `plan8`.`friends` (`user_a_id`, `user_b_id`, `status`) VALUES ('1', '2', '1'), ('2', '1', '1'), ('3', '1', '0'), ('1', '4', '0');

DROP TABLE IF EXISTS `events`;

CREATE TABLE `events` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(300) NOT NULL,
  `date_start` date NOT NULL,
  `time_start` time NOT NULL,
  `date_end` date NOT NULL,
  `time_end` time NOT NULL,
  `location` varchar(100) NOT NULL,
  `geolocation` point NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `date_created` date NOT NULL,
  `time_created` time NOT NULL,
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `plan8`.`events` (`id`, `title`, `description`, `date_start`, `time_start`, `date_end`, `time_end`, `location`, `geolocation`, `user_id`, `date_created`, `time_created`) VALUES (NULL, 'MOBICOM 1 FEATURE IMPLEMENTED', 'MOBICOM 1 FEATURE IMPLEMENTED', '2014-12-01', '12:45:00', '2014-12-01', '14:15:00', 'G301', GeomFromText('POINT(14.5662712 120.9929645)',0), '1', '2014-11-17', '22:42:00');
INSERT INTO `plan8`.`events` (`id`, `title`, `description`, `date_start`, `time_start`, `date_end`, `time_end`, `location`, `geolocation`, `user_id`, `date_created`, `time_created`) VALUES (NULL, 'MOBICOM WIREFRAME', 'MOBICOM WIREFRAME', '2014-11-10', '12:45:00', '2014-11-10', '14:15:00', 'G301', GeomFromText('POINT(14.5662712 120.9929645)',0), '1', '2014-11-17', '22:42:00');
INSERT INTO `plan8`.`events` (`id`, `title`, `description`, `date_start`, `time_start`, `date_end`, `time_end`, `location`, `geolocation`, `user_id`, `date_created`, `time_created`) VALUES (NULL, 'MOBICOM FINAL PRESENTATION', 'MOBICOM FINAL PRESENTATION', '2014-12-13', '00:00:00', '2014-12-18', '24:00:00', 'G301', GeomFromText('POINT(14.5662712 120.9929645)',0), '1', '2014-11-17', '22:42:00');

DROP TABLE IF EXISTS `invites`;

-- 0 invite, 1 going, 2 declined
CREATE TABLE `invites` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `event_id` int(11) unsigned NOT NULL,
  `status` int(1) NOT NULL,
  `geolocation` point,
  KEY `fk_event_idx` (`event_id`),
  KEY `fk_user_idx` (`user_id`),
  CONSTRAINT `fk_user_id1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_id1` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `plan8`.`invites` (`id`, `user_id`, `event_id`, `status`, `geolocation`) VALUES (NULL, '1', '1', '1', GeomFromText(NULL));
INSERT INTO `plan8`.`invites` (`id`, `user_id`, `event_id`, `status`, `geolocation`) VALUES (NULL, '1', '2', '1', GeomFromText(NULL));
INSERT INTO `plan8`.`invites` (`id`, `user_id`, `event_id`, `status`, `geolocation`) VALUES (NULL, '1', '3', '0', GeomFromText(NULL));

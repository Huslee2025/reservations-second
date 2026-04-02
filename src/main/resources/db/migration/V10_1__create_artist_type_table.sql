CREATE TABLE `artist_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artist_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_artist_type` (`artist_id`,`type_id`),
  KEY `idx_artist_type_artist_id` (`artist_id`),
  KEY `idx_artist_type_type_id` (`type_id`),
  CONSTRAINT `fk_artist_type_artist`
    FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`)
    ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT `fk_artist_type_type`
    FOREIGN KEY (`type_id`) REFERENCES `types` (`id`)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
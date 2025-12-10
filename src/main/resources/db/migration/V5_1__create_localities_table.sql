CREATE TABLE `localities` (
    `id`          INT(11)      NOT NULL AUTO_INCREMENT,
    `postal_code` VARCHAR(6)   NOT NULL,
    `locality`    VARCHAR(60)  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

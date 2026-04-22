CREATE TABLE `representation_reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reservation_id` int(11) NOT NULL,
  `representation_id` int(11) NOT NULL,
  `price_id` int(11) NOT NULL,
  `quantity` tinyint(4) NOT NULL,

  PRIMARY KEY (`id`),

  KEY `idx_rr_reservation_id` (`reservation_id`),
  KEY `idx_rr_representation_id` (`representation_id`),
  KEY `idx_rr_price_id` (`price_id`),

  UNIQUE KEY `uq_rr_res_rep_price` (`reservation_id`, `representation_id`, `price_id`),

  CONSTRAINT `fk_rr_reservation`
    FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`)
    ON UPDATE CASCADE ON DELETE RESTRICT,

  CONSTRAINT `fk_rr_representation`
    FOREIGN KEY (`representation_id`) REFERENCES `representations` (`id`)
    ON UPDATE CASCADE ON DELETE RESTRICT,

  CONSTRAINT `fk_rr_price`
    FOREIGN KEY (`price_id`) REFERENCES `prices` (`id`)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
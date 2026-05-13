
-- Structure de la table `reviews`

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `show_id` int(11) NOT NULL,
  `review` text,
  `stars` tinyint(5) unsigned NOT NULL,
  `validated` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- Index pour la table `reviews`

ALTER TABLE `reviews`
  ADD KEY `idx_reviews_user_id` (`user_id`),
  ADD KEY `idx_reviews_show_id` (`show_id`);


-- Contraintes pour la table `reviews`

ALTER TABLE `reviews`
  ADD CONSTRAINT `fk_reviews_user_id` FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT,
  ADD CONSTRAINT `fk_reviews_show_id` FOREIGN KEY (`show_id`)
    REFERENCES `shows` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;
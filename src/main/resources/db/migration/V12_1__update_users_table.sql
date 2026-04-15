ALTER TABLE `users`
  DROP COLUMN `role`,
  ADD COLUMN `updated_at` datetime NULL,
  ADD COLUMN `email_verified_at` datetime NULL,
  ADD COLUMN `remember_token` varchar(255) NULL;

-- Mettre à jour bob
UPDATE `users` SET 
  `login`='bob', 
  `password`='', 
  `firstname`='Bob', 
  `lastname`='Sull', 
  `email`='bob@sull.com', 
  `langue`='fr', 
  `created_at`='2010-01-01 12:00:00' 
WHERE id=1;

-- Transformer anna en lana
UPDATE `users` SET 
  `login`='lana', 
  `password`='', 
  `firstname`='Lana', 
  `lastname`='Sull', 
  `email`='lana@sull.com', 
  `langue`='fr', 
  `created_at`='2010-01-01 12:00:00' 
WHERE id=2;

-- Ajouter affiliate
INSERT INTO `users` (`id`, `login`, `password`, `firstname`, `lastname`, `email`, `langue`, `created_at`) VALUES
(3, 'affiliate', '', 'Affi', 'Liate', 'contact@affiliate.com', 'fr', '2020-01-01 12:00:00');
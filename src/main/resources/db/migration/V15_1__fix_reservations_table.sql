-- 1) Ajouter les colonnes manquantes (booking_date, status)
ALTER TABLE `reservations`
  ADD COLUMN `booking_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `user_id`,
  ADD COLUMN `status` varchar(60) NOT NULL DEFAULT 'PENDING' AFTER `booking_date`;

-- 2) Supprimer la FK + l'index liés à representation_id (anciens)
ALTER TABLE `reservations`
  DROP FOREIGN KEY `reservations_representation_id`;

ALTER TABLE `reservations`
  DROP INDEX `reservations_representation_id`;

-- 3) Supprimer les colonnes "ancienne logique"
ALTER TABLE `reservations`
  DROP COLUMN `representation_id`,
  DROP COLUMN `places`;
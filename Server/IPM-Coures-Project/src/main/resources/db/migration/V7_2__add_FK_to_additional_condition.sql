ALTER TABLE `additional_condition_places`
DROP FOREIGN KEY `additional_condition`;

ALTER TABLE `additional_condition_places`
ADD CONSTRAINT `additional_condition`
FOREIGN KEY (`additional_condition`)
REFERENCES `additional_condition` (`id`)
ON DELETE CASCADE
ON UPDATE CASCADE;
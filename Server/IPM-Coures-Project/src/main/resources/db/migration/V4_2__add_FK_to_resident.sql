ALTER TABLE `resident`
DROP FOREIGN KEY `room`;

ALTER TABLE `resident`
ADD CONSTRAINT `room`
FOREIGN KEY (`room`)
REFERENCES `room` (`id`)
ON DELETE CASCADE
ON UPDATE CASCADE;
ALTER TABLE `add_conditional_room`
    DROP FOREIGN KEY `room_condition`,
    DROP FOREIGN KEY `condition_room`;

ALTER TABLE `add_conditional_room`
    ADD CONSTRAINT `room_condition`
    FOREIGN KEY (`room`)
    REFERENCES `room` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    ADD CONSTRAINT `condition_room`
    FOREIGN KEY (`additional_condition`)
    REFERENCES `additional_condition` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
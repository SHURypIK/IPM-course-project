ALTER TABLE `add_conditional_floor`
    DROP FOREIGN KEY `condition_floor`,
    DROP FOREIGN KEY `floor_condition`;

ALTER TABLE `add_conditional_floor`
    ADD CONSTRAINT `condition_floor`
    FOREIGN KEY (`additional_condition`)
    REFERENCES `additional_condition` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    ADD CONSTRAINT `floor_condition`
    FOREIGN KEY (`floor`)
    REFERENCES `floor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
ALTER TABLE `add_conditional_block`
    DROP FOREIGN KEY `block_condition`,
    DROP FOREIGN KEY `condition_block`;

ALTER TABLE `add_conditional_block`
    ADD CONSTRAINT `block_condition`
    FOREIGN KEY (`block`)
    REFERENCES `block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    ADD CONSTRAINT `condition_block`
    FOREIGN KEY (`additional_condition`)
    REFERENCES `additional_condition` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
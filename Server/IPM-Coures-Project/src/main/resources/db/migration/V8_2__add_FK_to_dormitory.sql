ALTER TABLE `add_conditional_dormitory`
DROP FOREIGN KEY `condition_dormitory`,
DROP FOREIGN KEY `dormitory_condition`;

ALTER TABLE `add_conditional_dormitory`
ADD CONSTRAINT `condition_dormitory`
    FOREIGN KEY (`additional_condition`)
    REFERENCES `additional_condition` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
ADD CONSTRAINT `dormitory_condition`
    FOREIGN KEY (`dormitory`)
    REFERENCES `dormitory` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE `dormitory_responsible_persons`
DROP FOREIGN KEY `dormitory_person`,
DROP FOREIGN KEY `person_dormitory`;

ALTER TABLE `dormitory_responsible_persons`
ADD CONSTRAINT `dormitory_person`
    FOREIGN KEY (`dormitory`)
    REFERENCES `dormitory` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
ADD CONSTRAINT `person_dormitory`
    FOREIGN KEY (`responsible_persons`)
    REFERENCES `responsible_person` (`fio`)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

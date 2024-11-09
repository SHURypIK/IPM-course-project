ALTER TABLE person_position DROP CONSTRAINT FK_person;
ALTER TABLE person_position DROP CONSTRAINT FK_position;

ALTER TABLE person_position
    ADD CONSTRAINT FK_person FOREIGN KEY (person)
    REFERENCES responsible_person(fio)
    ON DELETE CASCADE;

ALTER TABLE person_position
    ADD CONSTRAINT FK_position FOREIGN KEY (position_i)
    REFERENCES position_t(id)
    ON DELETE CASCADE;
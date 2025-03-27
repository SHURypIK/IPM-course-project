INSERT INTO `floor` (`number`, `dormitory`, `responsible_person`) VALUES
    (1, 1, (SELECT fio FROM responsible_person WHERE fio = 'Евдокимов Сергей Викторович')),
    (2, 1, (SELECT fio FROM responsible_person WHERE fio = 'Иванов Иван Иванович')),
    (3, 1, (SELECT fio FROM responsible_person WHERE fio = 'Иванов Иван Иванович')),
    (4, 1, (SELECT fio FROM responsible_person WHERE fio = 'Козлов Алексей Владимирович')),
    (5, 1, (SELECT fio FROM responsible_person WHERE fio = 'Козлов Алексей Владимирович'));

INSERT INTO `add_conditional_floor` (`floor`, `additional_condition`) VALUES
    (1, 2),
    (1, 3),
    (2, 2),
    (2, 7),
    (3, 3),
    (4, 5),
    (4, 2),
    (5, 7);
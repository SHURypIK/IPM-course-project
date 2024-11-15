INSERT INTO dormitory (number, address) VALUES (1, 'ул. Пушкина, д. 10');

INSERT INTO dormitory_responsible_persons (dormitory, responsible_persons) VALUES
(1, (SELECT fio FROM responsible_person WHERE fio = 'Евдокимов Сергей Викторович')),
(1, (SELECT fio FROM responsible_person WHERE fio = 'Иванов Иван Иванович')),
(1, (SELECT fio FROM responsible_person WHERE fio = 'Козлов Алексей Владимирович')),
(1, (SELECT fio FROM responsible_person WHERE fio = 'Лебедев Андрей Валерьевич'));

INSERT INTO add_conditional_dormitory (dormitory, additional_condition) VALUES
(1, (SELECT id FROM additional_condition WHERE name = 'Библиотека')),
(1, (SELECT id FROM additional_condition WHERE name = 'Медицинский пункт')),
(1, (SELECT id FROM additional_condition WHERE name = 'Прачечная')),
(1, (SELECT id FROM additional_condition WHERE name = 'Столовая')),
(1, (SELECT id FROM additional_condition WHERE name = 'Туалет'));
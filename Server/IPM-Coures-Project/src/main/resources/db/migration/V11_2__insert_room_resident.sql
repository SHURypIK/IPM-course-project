UPDATE `resident` SET `status` = 'SETTLED', `room` = 1 WHERE `fio` = 'Иванов Иван Иванович';
UPDATE `resident` SET `status` = 'SETTLED', `room` = 1 WHERE `fio` = 'Петрова Анна Сергеевна';
UPDATE `resident` SET `status` = 'SETTLED', `room` = 2 WHERE `fio` = 'Сидоров Алексей Викторович';
UPDATE `resident` SET `status` = 'SETTLED', `room` = 2 WHERE `fio` = 'Козлова Мария Дмитриевна';
UPDATE `resident` SET `status` = 'SETTLED', `room` = 5 WHERE `fio` = 'Новиков Дмитрий Викторович';
UPDATE `resident` SET `status` = 'SETTLED', `room` = 5 WHERE `fio` = 'Лебедев Артем Павлович';
UPDATE `resident` SET `status` = 'SETTLED', `room` = 3 WHERE `fio` = 'Михайлова Екатерина Валентиновна';
UPDATE `resident` SET `status` = 'SETTLED', `room` = 3 WHERE `fio` = 'Шевченко Ирина Александровна';
UPDATE `resident` SET `status` = 'SETTLED', `room` = 4 WHERE `fio` = 'Попова Ольга Викторовна';
UPDATE `resident` SET `status` = 'SETTLED', `room` = 4 WHERE `fio` = 'Романова Татьяна Вячеславовна';




UPDATE `room` SET `number_of_available_places` = `number_of_available_places` - 1 WHERE `id` = 1;
UPDATE `room` SET `number_of_available_places` = `number_of_available_places` - 1 WHERE `id` = 1;
UPDATE `room` SET `number_of_available_places` = `number_of_available_places` - 1 WHERE `id` = 2;
UPDATE `room` SET `number_of_available_places` = `number_of_available_places` - 1 WHERE `id` = 2;
UPDATE `room` SET `number_of_available_places` = `number_of_available_places` - 1 WHERE `id` = 3;
UPDATE `room` SET `number_of_available_places` = `number_of_available_places` - 1 WHERE `id` = 3;
UPDATE `room` SET `number_of_available_places` = `number_of_available_places` - 1 WHERE `id` = 4;
UPDATE `room` SET `number_of_available_places` = `number_of_available_places` - 1 WHERE `id` = 4;
UPDATE `room` SET `number_of_available_places` = `number_of_available_places` - 1 WHERE `id` = 5;
UPDATE `room` SET `number_of_available_places` = `number_of_available_places` - 1 WHERE `id` = 5;

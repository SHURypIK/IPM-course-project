INSERT INTO medical_report (certificate_number, fit, doctor, valid_until, type, patient) VALUES
('ID100123', TRUE, 'Доктор Сидоров', '2024-12-31', 'INFECTIOUS_DISEASES', (SELECT fio FROM resident WHERE fio = 'Иванов Иван Иванович')),
('ID100124', TRUE, 'Доктор Петров', '2024-06-30', 'FLUOROGRAPHY', (SELECT fio FROM resident WHERE fio = 'Иванов Иван Иванович')),
('ID100125', FALSE, 'Доктор Иванова', '2023-12-31', 'DERMATOLOGIST', (SELECT fio FROM resident WHERE fio = 'Иванов Иван Иванович')),
('ID100126', TRUE, 'Доктор Смирнова', '2024-12-31', 'INFECTIOUS_DISEASES', (SELECT fio FROM resident WHERE fio = 'Петрова Анна Сергеевна')),
('ID100127', TRUE, 'Доктор Козлов', '2024-06-30', 'FLUOROGRAPHY', (SELECT fio FROM resident WHERE fio = 'Петрова Анна Сергеевна')),
('ID100128', TRUE, 'Доктор Николаева', '2024-12-31', 'DERMATOLOGIST', (SELECT fio FROM resident WHERE fio = 'Петрова Анна Сергеевна')),
('ID100129', FALSE, 'Доктор Михайлов', '2023-12-31', 'INFECTIOUS_DISEASES', (SELECT fio FROM resident WHERE fio = 'Сидоров Алексей Викторович')),
('ID100130', TRUE, 'Доктор Павлов', '2024-06-30', 'FLUOROGRAPHY', (SELECT fio FROM resident WHERE fio = 'Сидоров Алексей Викторович')),
('ID100131', FALSE, 'Доктор Андреева', '2023-12-31', 'DERMATOLOGIST', (SELECT fio FROM resident WHERE fio = 'Сидоров Алексей Викторович')),
('ID100132', TRUE, 'Доктор Попов', '2024-12-31', 'INFECTIOUS_DISEASES', (SELECT fio FROM resident WHERE fio = 'Козлова Мария Дмитриевна')),
('ID100133', FALSE, 'Доктор Романова', '2023-06-30', 'FLUOROGRAPHY', (SELECT fio FROM resident WHERE fio = 'Козлова Мария Дмитриевна')),
('ID100134', TRUE, 'Доктор Евгеньев', '2024-12-31', 'DERMATOLOGIST', (SELECT fio FROM resident WHERE fio = 'Козлова Мария Дмитриевна')),
('ID100135', TRUE, 'Доктор Светлова', '2024-12-31', 'INFECTIOUS_DISEASES', (SELECT fio FROM resident WHERE fio = 'Михайлова Екатерина Валентиновна')),
('ID100136', TRUE, 'Доктор Сергеев', '2024-06-30', 'FLUOROGRAPHY', (SELECT fio FROM resident WHERE fio = 'Михайлова Екатерина Валентиновна')),
('ID100137', FALSE, 'Доктор Сидоров', '2023-12-31', 'DERMATOLOGIST', (SELECT fio FROM resident WHERE fio = 'Михайлова Екатерина Валентиновна'));
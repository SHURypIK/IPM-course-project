INSERT INTO `room` (`designed_for`, `number`, `number_of_available_places`, `number_of_places`, `block`) VALUES
    -- Block 1 (MALE)
    ('MALE', 1011, 4, 4, 1),
    ('MALE', 1012, 4, 4, 1),

    -- Block 2 (FEMALE)
    ('FEMALE', 1021, 4, 4, 2),
    ('FEMALE', 1022, 4, 4, 2),

    -- Block 3 (MALE)
    ('MALE', 1031, 4, 4, 3),
    ('MALE', 1032, 4, 4, 3),

    -- Block 4 (FEMALE)
    ('FEMALE', 1041, 4, 4, 4),
    ('FEMALE', 1042, 4, 4, 4),

    -- Block 5 (MALE)
    ('MALE', 1051, 4, 4, 5),
    ('MALE', 1052, 4, 4, 5),

    -- Block 6 (FEMALE)
    ('FEMALE', 2011, 4, 4, 6),
    ('FEMALE', 2012, 4, 4, 6),

    -- Block 7 (MALE)
    ('MALE', 2021, 4, 4, 7),
    ('MALE', 2022, 4, 4, 7),

    -- Block 8 (FEMALE)
    ('FEMALE', 2031, 4, 4, 8),
    ('FEMALE', 2032, 4, 4, 8),

    -- Block 9 (MALE)
    ('MALE', 2041, 4, 4, 9),
    ('MALE', 2042, 4, 4, 9),

    -- Block 10 (FEMALE)
    ('FEMALE', 2051, 4, 4, 10),
    ('FEMALE', 2052, 4, 4, 10),

    -- Block 11 (MALE)
    ('MALE', 3011, 4, 4, 11),
    ('MALE', 3012, 4, 4, 11),

    -- Block 12 (FEMALE)
    ('FEMALE', 3021, 4, 4, 12),
    ('FEMALE', 3022, 4, 4, 12),

    -- Block 13 (MALE)
    ('MALE', 3031, 4, 4, 13),
    ('MALE', 3032, 4, 4, 13),

    -- Block 14 (FEMALE)
    ('FEMALE', 3041, 4, 4, 14),
    ('FEMALE', 3042, 4, 4, 14),

    -- Block 15 (MALE)
    ('MALE', 3051, 4, 4, 15),
    ('MALE', 3052, 4, 4, 15),

    -- Block 16 (FEMALE)
    ('FEMALE', 4011, 4, 4, 16),
    ('FEMALE', 4012, 4, 4, 16),

    -- Block 17 (MALE)
    ('MALE', 4021, 4, 4, 17),
    ('MALE', 4022, 4, 4, 17),

    -- Block 18 (FEMALE)
    ('FEMALE', 4031, 4, 4, 18),
    ('FEMALE', 4032, 4, 4, 18),

    -- Block 19 (MALE)
    ('MALE', 4041, 4, 4, 19),
    ('MALE', 4042, 4, 4, 19),

    -- Block 20 (FEMALE)
    ('FEMALE', 4051, 4, 4, 20),
    ('FEMALE', 4052, 4, 4, 20),

    -- Block 21 (MALE)
    ('MALE', 5011, 4, 4, 21),
    ('MALE', 5012, 4, 4, 21),

    -- Block 22 (FEMALE)
    ('FEMALE', 5021, 4, 4, 22),
    ('FEMALE', 5022, 4, 4, 22),

    -- Block 23 (MALE)
    ('MALE', 5031, 4, 4, 23),
    ('MALE', 5032, 4, 4, 23),

    -- Block 24 (FEMALE)
    ('FEMALE', 5041, 4, 4, 24),
    ('FEMALE', 5042, 4, 4, 24),

    -- Block 25 (MALE)
    ('MALE', 5051, 4, 4, 25),
    ('MALE', 5052, 4, 4, 25);


INSERT INTO `add_conditional_room` (`room`, `additional_condition`) VALUES
    (1, 2),
    (1, 4),
    (2, 4),
    (2, 6),
    (3, 6),
    (4, 2),
    (4, 4),
    (5, 4);
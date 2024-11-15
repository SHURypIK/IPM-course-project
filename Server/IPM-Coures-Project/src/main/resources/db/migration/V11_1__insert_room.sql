INSERT INTO `room` (`designed_for`, `number`, `number_of_available_places`, `number_of_places`, `block`) VALUES
    -- Block 1 (MALE)
    ('MALE', 101, 4, 4, 1),
    ('MALE', 102, 4, 4, 1),

    -- Block 2 (FEMALE)
    ('FEMALE', 201, 4, 4, 2),
    ('FEMALE', 202, 4, 4, 2),

    -- Block 3 (MALE)
    ('MALE', 301, 4, 4, 3),
    ('MALE', 302, 4, 4, 3),

    -- Block 4 (FEMALE)
    ('FEMALE', 401, 4, 4, 4),
    ('FEMALE', 402, 4, 4, 4),

    -- Block 5 (MALE)
    ('MALE', 501, 4, 4, 5),
    ('MALE', 502, 4, 4, 5),

    -- Block 6 (FEMALE)
    ('FEMALE', 601, 4, 4, 6),
    ('FEMALE', 602, 4, 4, 6),

    -- Block 7 (MALE)
    ('MALE', 701, 4, 4, 7),
    ('MALE', 702, 4, 4, 7),

    -- Block 8 (FEMALE)
    ('FEMALE', 801, 4, 4, 8),
    ('FEMALE', 802, 4, 4, 8),

    -- Block 9 (MALE)
    ('MALE', 901, 4, 4, 9),
    ('MALE', 902, 4, 4, 9),

    -- Block 10 (FEMALE)
    ('FEMALE', 1001, 4, 4, 10),
    ('FEMALE', 1002, 4, 4, 10),

    -- Block 11 (MALE)
    ('MALE', 1101, 4, 4, 11),
    ('MALE', 1102, 4, 4, 11),

    -- Block 12 (FEMALE)
    ('FEMALE', 1201, 4, 4, 12),
    ('FEMALE', 1202, 4, 4, 12),

    -- Block 13 (MALE)
    ('MALE', 1301, 4, 4, 13),
    ('MALE', 1302, 4, 4, 13),

    -- Block 14 (FEMALE)
    ('FEMALE', 1401, 4, 4, 14),
    ('FEMALE', 1402, 4, 4, 14),

    -- Block 15 (MALE)
    ('MALE', 1501, 4, 4, 15),
    ('MALE', 1502, 4, 4, 15),

    -- Block 16 (FEMALE)
    ('FEMALE', 1601, 4, 4, 16),
    ('FEMALE', 1602, 4, 4, 16),

    -- Block 17 (MALE)
    ('MALE', 1701, 4, 4, 17),
    ('MALE', 1702, 4, 4, 17),

    -- Block 18 (FEMALE)
    ('FEMALE', 1801, 4, 4, 18),
    ('FEMALE', 1802, 4, 4, 18),

    -- Block 19 (MALE)
    ('MALE', 1901, 4, 4, 19),
    ('MALE', 1902, 4, 4, 19),

    -- Block 20 (FEMALE)
    ('FEMALE', 2001, 4, 4, 20),
    ('FEMALE', 2002, 4, 4, 20),

    -- Block 21 (MALE)
    ('MALE', 2101, 4, 4, 21),
    ('MALE', 2102, 4, 4, 21),

    -- Block 22 (FEMALE)
    ('FEMALE', 2201, 4, 4, 22),
    ('FEMALE', 2202, 4, 4, 22),

    -- Block 23 (MALE)
    ('MALE', 2301, 4, 4, 23),
    ('MALE', 2302, 4, 4, 23),

    -- Block 24 (FEMALE)
    ('FEMALE', 2401, 4, 4, 24),
    ('FEMALE', 2402, 4, 4, 24),

    -- Block 25 (MALE)
    ('MALE', 2501, 4, 4, 25),
    ('MALE', 2502, 4, 4, 25);


INSERT INTO `add_conditional_room` (`room`, `additional_condition`) VALUES
    (1, 2),
    (1, 4),
    (2, 4),
    (2, 6),
    (3, 6),
    (4, 2),
    (4, 4),
    (5, 4);
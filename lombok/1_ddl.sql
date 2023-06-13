-- CREATE TABLE  table_name(
--     column_name_1 integer constraints,
--     column_name_2 integer constraints,
--     column_name_3 integer
-- );

CREATE TABLE students(
    id INTEGER,
    name VARCHAR(32),
    email TEXT
);

CREATE TABLE students_2(
    id INTEGER,
    username TEXT,
    first_name TEXT,
    last_name TEXT,
    email TEXT
);

CREATE TABLE students_2(
    id INTEGER,
    username TEXT,
    first_name TEXT,
    last_name TEXT,
    email TEXT NOT NULL
);

CREATE TABLE students_unique(
    id INTEGER UNIQUE,
    username TEXT
);

CREATE TABLE students_pka(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    username TEXT,
    first_name TEXT,
    last_name TEXT,
    email TEXT
);

CREATE TABLE students(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    username TEXT UNIQUE,
    first_name TEXT,
    last_name TEXT,
    email TEXT NOT NULL
);

ALTER TABLE students_2 RENAME TO students_temp;
ALTER TABLE students_temp RENAME TO students_2;

ALTER TABLE students_2 RENAME COLUMN first_name TO given_name;
ALTER TABLE students_2 RENAME COLUMN last_name TO sur_name;

ALTER TABLE students_2 ADD COLUMN phone VARCHAR(64);
ALTER TABLE students_2 ADD COLUMN phone_2 VARCHAR(64) NOT NULL;
ALTER TABLE students_2 ADD COLUMN phone_3 VARCHAR(64) NOT NULL DEFAULT '';

ALTER TABLE students_2 DROP COLUMN phone_3;

DROP TABLE students_2;
DROP TABLE IF EXISTS students_2;





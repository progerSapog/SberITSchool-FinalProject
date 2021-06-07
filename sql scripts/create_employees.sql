/* Создание таблицы кафедр работников */
CREATE TABLE VST_ADMIN.USERS
(
    id                 INTEGER NOT NULL,
    email              VARCHAR2(100) NOT NULL,
    password           VARCHAR2(100) NOT NULL,
    first_name         VARCHAR2(100) NOT NULL,
    last_name          VARCHAR2(150) NOT NULL,
    middle_name        VARCHAR2(150) NOT NULL,
    academic_degree_id INTEGER NOT NULL,
    cathedra_id        INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (academic_degree_id) REFERENCES VST_ADMIN.ACADEMIC_DEGREE (id),
    FOREIGN KEY (cathedra_id) REFERENCES VST_ADMIN.CATHEDRA (id)
);

/* Генератор для id таблицы EMPLOYEES на основе SEQUENCE*/
CREATE SEQUENCE sq_users_table
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

/* Создание тригера для перед вставкой в таблицу EMPLOYEES */
CREATE OR REPLACE TRIGGER tr_users_table BEFORE INSERT ON VST_ADMIN.USERS
    FOR EACH ROW
BEGIN
SELECT sq_users_table.nextval
INTO :new.id
FROM SYS.DUAL;
end;


INSERT INTO VST_ADMIN.USERS
VALUES ('', 'vst@gmail.com', 'qwe123', 'Дмитрий', 'Жевнерчук', 'Валерьевич', 8, 1);
INSERT INTO VST_ADMIN.USERS
VALUES ('', 'martynov@gmail.com', 'qwe123', 'Мартынов', 'Дмитрий', 'Сергеевич', 5, 1);

COMMIT;


SELECT *
FROM VST_ADMIN.USERS;

SELECT VST_ADMIN.USERS.first_name  AS Имя,
       VST_ADMIN.USERS.last_name   AS Фамилия,
       VST_ADMIN.USERS.middle_name AS Отчество,
       ACADEMIC_DEGREE.NAME  AS Должность,
       CATHEDRA.NAME         AS Кафедра
FROM VST_ADMIN.USERS
         LEFT JOIN VST_ADMIN.ACADEMIC_DEGREE ON VST_ADMIN.USERS.academic_degree_id = ACADEMIC_DEGREE.id
         LEFT JOIN VST_ADMIN.CATHEDRA ON VST_ADMIN.USERS.cathedra_id = CATHEDRA.id
ORDER BY first_name;


DROP TRIGGER tr_users_table;
DROP SEQUENCE sq_users_table;
DROP TABLE VST_ADMIN.USERS;



COMMIT;
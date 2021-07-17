CREATE DATABASE VST_TEST;

/***********************************************************************/
/* Создание таблицы кафедр ИРИТа */
CREATE TABLE VST_TEST.DEPARTMENT
(
    id   INTEGER NOT NULL,
    name VARCHAR2(150) NOT NULL,
    PRIMARY KEY (id)
);

/* Вставка кафедр ИРИТа */
INSERT INTO VST_TEST.DEPARTMENT
VALUES (1, 'Вычислительные системы и технологии');
INSERT INTO VST_TEST.DEPARTMENT
VALUES (2, 'Графические информационные системы');
INSERT INTO VST_TEST.DEPARTMENT
VALUES (3, 'Информационные радиосистемы');
INSERT INTO VST_TEST.DEPARTMENT
VALUES (4, 'Информатика и системы управления');
INSERT INTO VST_TEST.DEPARTMENT
VALUES (5, 'Компьютерные технологии в проектировании и производстве');
INSERT INTO VST_TEST.DEPARTMENT
VALUES (6, 'Прикладная математика');
INSERT INTO VST_TEST.DEPARTMENT
VALUES (7, 'Электроника и сети ЭВМ');
INSERT INTO VST_TEST.DEPARTMENT
VALUES (8, 'Информационная безопастность вычислительных систем и сетей');
/***********************************************************************/

CREATE TABLE VST_TEST.ACADEMIC_DEGREE
(
    id   INTEGER NOT NULL,
    name VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id)
);

/* Вставка всех возможных ученых степеней */
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (11, 'лаборант');
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (10, 'страший лаборант');
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (9, 'ассистент');
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (8, 'преподаватель');
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (7, 'страший преподаватель');
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (6, 'доцент');
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (5, 'профессор');
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (4, 'завкафедрой');
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (3, 'декан');
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (2, 'проректор');
INSERT INTO VST_TEST.ACADEMIC_DEGREE
VALUES (1, 'ректор');
/***********************************************************************/

/***********************************************************************/
/* Создание таблицы ролей на сайте */
CREATE TABLE VST_TEST.ROLE
(
    id   INTEGER NOT NULL,
    name VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id)
);


INSERT INTO VST_TEST.ROLE
VALUES (1, 'ROLE_ADMIN');
INSERT INTO VST_TEST.ROLE
VALUES (2, 'ROLE_USER');
/***********************************************************************/

/* Создание таблицы кафедр работников */
CREATE TABLE VST_TEST.USERS
(
    id                 INTEGER NOT NULL,
    email              VARCHAR2(100) NOT NULL,
    password           VARCHAR2(100) NOT NULL,
    first_name         VARCHAR2(100) NOT NULL,
    last_name          VARCHAR2(150) NOT NULL,
    middle_name        VARCHAR2(150) NOT NULL,
    academic_degree_id INTEGER,
    department_id      INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (academic_degree_id) REFERENCES VST_TEST.ACADEMIC_DEGREE (id),
    FOREIGN KEY (department_id) REFERENCES VST_TEST.DEPARTMENT (id)
);

INSERT INTO VST_TEST.USERS
VALUES (1, 'adminTest@gmail.com', '$2y$12$xcJawS6MgXm7uRsed6TFB.ADvqXIZfwd7c8DZZvQuOeT3UHi7lj0q', 'AdminTest1', 'AdminTest1', 'AdminTest1', 6, 1);

INSERT INTO VST_TEST.USERS
VALUES (2, 'Petr@gmail.com', '$2y$12$tsp.vj/WWRAxkwKbhc1WaOjUNYNLd4uRDIQlymjAuoGEsZJ65aHfy ', 'PetrTest1', 'PetrTest1', 'PetrTest1', 7, 2);

CREATE TABLE VST_TEST.USER_ROLE
(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    CONSTRAINT emp_role_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_emp
        FOREIGN KEY (user_id)
            REFERENCES VST_TEST.USERS (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_role
        FOREIGN KEY (role_id)
            REFERENCES VST_TEST.ROLE (id)
            ON DELETE CASCADE
);

/* Даем админу права админа */
INSERT INTO VST_TEST.USER_ROLE
VALUES (1, 1);

/* Даем админу права админа */
INSERT INTO VST_TEST.USER_ROLE
VALUES (2, 2);

/* Создание таблицы заявок */
CREATE TABLE VST_TEST.APPLICATIONS
(
    id              INTEGER NOT NULL,
    audience_number INTEGER NOT NULL,
    text            CLOB    NOT NULL,
    user_id         INTEGER NOT NULL,
    CONSTRAINT apl_pk PRIMARY KEY (id),
    CONSTRAINT fk_Apl_eml
        FOREIGN KEY (user_id)
            REFERENCES VST_TEST.USERS (id)
            ON DELETE CASCADE
);

INSERT INTO VST_TEST.APPLICATIONS
VALUES (1, 1234, 'Тестовая заявка №1', 1);

INSERT INTO VST_TEST.APPLICATIONS
VALUES (2, 2345, 'Тестовая заявка №2', 2);

COMMIT;
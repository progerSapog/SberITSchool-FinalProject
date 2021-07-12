ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;
CREATE USER VST_ADMIN IDENTIFIED BY vst1243;
GRANT ALL PRIVILEGES TO VST_ADMIN;

/***********************************************************************/
/* Создание таблицы кафедр ИРИТа */
CREATE TABLE VST_ADMIN.DEPARTMENT
(
    id   INTEGER NOT NULL,
    name VARCHAR2(150) NOT NULL,
    PRIMARY KEY (id)
);

/* Генератор для id таблицы CATHEDRA на основе SEQUENCE*/
CREATE SEQUENCE VST_ADMIN.sq_department_table
    START WITH 9
    INCREMENT BY 1
    NOMAXVALUE;

/* Вставка кафедр ИРИТа */
INSERT INTO VST_ADMIN.DEPARTMENT
VALUES (1, 'Вычислительные системы и технологии');
INSERT INTO VST_ADMIN.DEPARTMENT
VALUES (2, 'Графические информационные системы');
INSERT INTO VST_ADMIN.DEPARTMENT
VALUES (3, 'Информационные радиосистемы');
INSERT INTO VST_ADMIN.DEPARTMENT
VALUES (4, 'Информатика и системы управления');
INSERT INTO VST_ADMIN.DEPARTMENT
VALUES (5, 'Компьютерные технологии в проектировании и производстве');
INSERT INTO VST_ADMIN.DEPARTMENT
VALUES (6, 'Прикладная математика');
INSERT INTO VST_ADMIN.DEPARTMENT
VALUES (7, 'Электроника и сети ЭВМ');
INSERT INTO VST_ADMIN.DEPARTMENT
VALUES (8, 'Информационная безопастность вычислительных систем и сетей');
/***********************************************************************/

/***********************************************************************/
/* Создание таблицы ученый степеней (должностей внутри ВУЗа) */
CREATE TABLE VST_ADMIN.ACADEMIC_DEGREE
(
    id   INTEGER NOT NULL,
    name VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id)
);

/* Генератор для id таблицы ACADEMIC_DEGREE на основе SEQUENCE*/
CREATE SEQUENCE VST_ADMIN.sq_degree_table
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

/* Вставка всех возможных ученых степеней */
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (11, 'лаборант');
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (10, 'страший лаборант');
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (9, 'ассистент');
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (8, 'преподаватель');
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (7, 'страший преподаватель');
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (6, 'доцент');
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (5, 'профессор');
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (4, 'завкафедрой');
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (3, 'декан');
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (2, 'проректор');
INSERT INTO VST_ADMIN.ACADEMIC_DEGREE
VALUES (1, 'ректор');
/***********************************************************************/

/***********************************************************************/
/* Создание таблицы ролей на сайте */
CREATE TABLE VST_ADMIN.ROLE
(
    id   INTEGER NOT NULL,
    name VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id)
);

/* Генератор для id таблицы ROLE на основе SEQUENCE*/
CREATE SEQUENCE VST_ADMIN.sq_role_table
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

INSERT INTO VST_ADMIN.ROLE
VALUES (1, 'ROLE_ADMIN');
INSERT INTO VST_ADMIN.ROLE
VALUES (2, 'ROLE_USER');
/***********************************************************************/

/***********************************************************************/
/* Создание таблицы кафедр работников */
CREATE TABLE VST_ADMIN.USERS
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
    FOREIGN KEY (academic_degree_id) REFERENCES VST_ADMIN.ACADEMIC_DEGREE (id),
    FOREIGN KEY (department_id) REFERENCES VST_ADMIN.DEPARTMENT (id)
);

/* Генератор для id таблицы EMPLOYEES на основе SEQUENCE*/
CREATE SEQUENCE VST_ADMIN.sq_users_table
    START WITH 2
    INCREMENT BY 1
    NOMAXVALUE;

/* Вставка Admin */
/* email:admin@gmail.com password: admin */
INSERT INTO VST_ADMIN.USERS
VALUES (1, 'admin@gmail.com', '$2y$12$xcJawS6MgXm7uRsed6TFB.ADvqXIZfwd7c8DZZvQuOeT3UHi7lj0q', 'Admin1', 'Admin1', 'Admin1', 6, 1);
/***********************************************************************/

/***********************************************************************/
/* Создание таблицы соединений между пользователяими и ролями */
CREATE TABLE VST_ADMIN.USER_ROLE
(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    CONSTRAINT emp_role_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_emp
        FOREIGN KEY (user_id)
            REFERENCES VST_ADMIN.USERS (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_role
        FOREIGN KEY (role_id)
            REFERENCES VST_ADMIN.ROLE (id)
            ON DELETE CASCADE
);

/* Даем админу права админа */
INSERT INTO VST_ADMIN.USER_ROLE
VALUES (1, 1);
/***********************************************************************/

/***********************************************************************/
/* Создание таблицы заявок */
CREATE TABLE VST_ADMIN.APPLICATIONS
(
    id              INTEGER NOT NULL,
    audience_number INTEGER NOT NULL,
    text            CLOB    NOT NULL,
    user_id         INTEGER NOT NULL,
    CONSTRAINT apl_pk PRIMARY KEY (id),
    CONSTRAINT fk_Apl_eml
        FOREIGN KEY (user_id)
            REFERENCES VST_ADMIN.USERS (id)
            ON DELETE CASCADE
);

/* Генератор для id таблицы APPLICATIONS на основе SEQUENCE*/
CREATE SEQUENCE VST_ADMIN.sq_apl_table
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

COMMIT;

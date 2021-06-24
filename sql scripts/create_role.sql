/* Создание таблицы ролей на сайте */
CREATE TABLE VST_ADMIN.ROLE
(
    id   INTEGER NOT NULL,
    name VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id)
);

/* Генератор для id таблицы ROLE на основе SEQUENCE*/
CREATE SEQUENCE sq_role_table
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

/* Создание тригера для перед вставкой в таблицу ROLE */
CREATE OR REPLACE TRIGGER tr_role_table BEFORE INSERT ON VST_ADMIN.ROLE
    FOR EACH ROW
BEGIN
SELECT sq_role_table.nextval
INTO :new.id
FROM SYS.DUAL;
end;

INSERT INTO VST_ADMIN.ROLE
VALUES ('', 'ROLE_ADMIN');
INSERT INTO VST_ADMIN.ROLE
VALUES ('', 'ROLE_USER');

COMMIT;

SELECT * FROM VST_ADMIN.ROLE;

DELETE FROM VST_ADMIN.ROLE WHERE id = 22;

DROP TRIGGER tr_role_table;
DROP SEQUENCE sq_role_table;
DROP TABLE VST_ADMIN.ROLE;


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
CREATE SEQUENCE sq_apl_table
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

/* Создание тригера для перед вставкой в таблицу APPLICATIONS */
CREATE OR REPLACE TRIGGER tr_apl_table BEFORE INSERT ON VST_ADMIN.APPLICATIONS
    FOR EACH ROW
BEGIN
SELECT sq_apl_table.nextval
INTO :new.id
FROM SYS.DUAL;
end;

INSERT INTO VST_ADMIN.APPLICATIONS
VALUES ('', 5424, 'Установить Microsoft Office на компьютер №12', 2);
INSERT INTO VST_ADMIN.APPLICATIONS
VALUES ('', 6125, 'Установить Microsoft Office на компьютер №13', 1);

COMMIT;

SELECT * FROM VST_ADMIN.APPLICATIONS;

DROP TRIGGER tr_apl_table;
DROP SEQUENCE sq_apl_table;
DROP TABLE VST_ADMIN.APPLICATIONS;
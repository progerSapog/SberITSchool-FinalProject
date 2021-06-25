/* Создание таблицы кафедр ИРИТа */
CREATE TABLE VST_ADMIN.CATHEDRA
(
    id   INTEGER NOT NULL,
    name VARCHAR2(150) NOT NULL,
    PRIMARY KEY (id)
);

/* Генератор для id таблицы CATHEDRA на основе SEQUENCE*/
CREATE SEQUENCE sq_cathedra_table
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;

/* Вставка кафедр ИРИТа */
INSERT INTO VST_ADMIN.CATHEDRA
VALUES ('', 'Вычислительные системы и технологии');
INSERT INTO VST_ADMIN.CATHEDRA
VALUES ('', 'Графические информационные системы');
INSERT INTO VST_ADMIN.CATHEDRA
VALUES ('', 'Информационные радиосистемы');
INSERT INTO VST_ADMIN.CATHEDRA
VALUES ('', 'Информатика и системы управления');
INSERT INTO VST_ADMIN.CATHEDRA
VALUES ('', 'Компьютерные технологии в проектировании и производстве');
INSERT INTO VST_ADMIN.CATHEDRA
VALUES ('', 'Прикладная математика');
INSERT INTO VST_ADMIN.CATHEDRA
VALUES ('', 'Электроника и сети ЭВМ');
INSERT INTO VST_ADMIN.CATHEDRA
VALUES ('', 'Информационная безопастность вычислительных систем и сетей');

COMMIT;

SELECT *
FROM VST_ADMIN.CATHEDRA;


DROP TRIGGER tr_cathedra_table;
DROP SEQUENCE sq_cathedra_table;
DROP TABLE VST_ADMIN.CATHEDRA;
/* Создание таблицы ученый степеней (должностей внутри ВУЗа) */
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

INSERT INTO VST_ADMIN.USER_ROLE
VALUES (1, 1);
INSERT INTO VST_ADMIN.USER_ROLE
VALUES (2, 2);
INSERT INTO VST_ADMIN.USER_ROLE
VALUES (63, 2);

COMMIT;

SELECT *
FROM VST_ADMIN.USER_ROLE;


DROP TABLE VST_ADMIN.USER_ROLE;
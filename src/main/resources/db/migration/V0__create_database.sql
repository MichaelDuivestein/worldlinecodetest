DROP TABLE IF EXISTS task_list_user;
DROP TABLE IF EXISTS task_list_item;
DROP TABLE IF EXISTS task_list;
DROP TABLE IF EXISTS users;

-- this should probably have a self-referring created_by column (or at least an audit_log table)
CREATE TABLE users (
    id                  UUID            NOT NULL DEFAULT RANDOM_UUID() PRIMARY KEY,
    created_date        TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_date        TIMESTAMP       NULL,
    updated_by_user     UUID            NULL,
    user_email          VARCHAR(160)    NOT NULL,
    user_display_name   VARCHAR(160)    NOT NULL UNIQUE,
    FOREIGN KEY (updated_by_user) REFERENCES users(id)
);

CREATE TABLE task_list
(
    id              UUID            NOT NULL DEFAULT RANDOM_UUID() PRIMARY KEY,
    created_date    TIMESTAMP       NOT NULL DEFAULT NOW(),
    created_by_user UUID            NOT NULL,
    updated_date    TIMESTAMP       NULL,
    updated_by_user UUID            NULL,
    list_name       VARCHAR(120)    NOT NULL,
    FOREIGN KEY (created_by_user) REFERENCES users(id),
    FOREIGN KEY (updated_by_user) REFERENCES users(id)
);

CREATE TABLE task_list_user (
    id              UUID        NOT NULL DEFAULT RANDOM_UUID() PRIMARY KEY,
    created_date    TIMESTAMP   NOT NULL DEFAULT NOW(),
    created_by_user UUID        NOT NULL,
    updated_date    TIMESTAMP   NULL,
    updated_by_user UUID        NULL,
    task_list_id    UUID        NOT NULL,
    user_id         UUID        NOT NULL,
    is_owner        BOOLEAN     NOT NULL DEFAULT FALSE,
    FOREIGN KEY (created_by_user) REFERENCES users(id),
    FOREIGN KEY (updated_by_user) REFERENCES users(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (task_list_id) REFERENCES task_list(id),
    UNIQUE (task_list_id, user_id)
);

CREATE TABLE task_list_item (
    id                  UUID                    NOT NULL DEFAULT RANDOM_UUID() PRIMARY KEY,
    created_date        TIMESTAMP               NOT NULL DEFAULT NOW(),
    created_by_user     UUID                    NOT NULL,
    updated_date        TIMESTAMP               NULL,
    updated_by_user     UUID                    NULL,
    task_list_id        UUID                    NOT NULl,
    task_name           VARCHAR(120)            NOT NULL,
    task_description    CHARACTER LARGE OBJECT  NULL,
    FOREIGN KEY (created_by_user) REFERENCES users(id),
    FOREIGN KEY (updated_by_user) REFERENCES users(id),
    FOREIGN KEY (task_list_id) REFERENCES task_list(id)
);

COMMIT;
CREATE TABLE album
(
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    release_date TIMESTAMP WITHOUT TIME ZONE
);
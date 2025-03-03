CREATE TABLE user_details
(
    id            BINARY(16)  NOT NULL PRIMARY KEY,
    kilograms     DOUBLE,
    height        DOUBLE,
    age           INT,
    workout_state VARCHAR(255),
    gender        VARCHAR(255),
    user_id       BINARY(16) NOT NULL
);
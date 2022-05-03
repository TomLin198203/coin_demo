DROP TABLE IF EXISTS t_coin;
CREATE TABLE t_coin(
    id BIGINT AUTO_INCREMENT,
    code VARCHAR(25),
    name VARCHAR(25),
    rate DOUBLE,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);
-- DROP TABLE IF EXISTS t_user;
-- CREATE TABLE t_user(
--     id BIGINT AUTO_INCREMENT,
--     name VARCHAR(25),
--     pwd VARCHAR(25),
--     age DOUBLE,
--     PRIMARY KEY(id)
-- );
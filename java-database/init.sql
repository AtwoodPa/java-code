-- 创建数据表
CREATE TABLE user
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    age        INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- 测试语句
INSERT INTO user (username, email, age)
VALUES ('Alice', 'alice@example.com', 30),
       ('Bob', 'bob@example.com', 25),
       ('Charlie', 'charlie@example.com', 35),
       ('David', 'david@example.com', 40),
       ('Eve', 'eve@example.com', 28),
       ('Frank', 'frank@example.com', 33),
       ('Grace', 'grace@example.com', 27),
       ('Heidi', 'heidi@example.com', 31),
       ('Ivan', 'ivan@example.com', 29),
       ('Judy', 'judy@example.com', 32),
       ('Ken', 'ken@example.com', 26),
       ('Laura', 'laura@example.com', 34),
       ('Mallory', 'mallory@example.com', 37),
       ('Niaj', 'niaj@example.com', 30),
       ('Olivia', 'olivia@example.com', 24),
       ('Peggy', 'peggy@example.com', 28),
       ('Quentin', 'quentin@example.com', 36),
       ('Rita', 'rita@example.com', 27),
       ('Sam', 'sam@example.com', 31),
       ('Trent', 'trent@example.com', 33);

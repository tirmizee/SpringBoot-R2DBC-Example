DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
    id INT PRIMARY KEY IDENTITY (1, 1),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);
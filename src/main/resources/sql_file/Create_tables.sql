Create DATABASE company_db;
USE company_db;

CREATE TABLE companies
(
    company_id INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL
);

CREATE TABLE employees
(
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    job_title   VARCHAR(50) NOT NULL,
    company_id  INT         NOT NULL,
    Foreign Key fk_company_id (company_id) REFERENCES companies (company_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);






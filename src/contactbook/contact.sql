CREATE DATABASE contacts;
USE contacts;
CREATE TABLE contacts (
                          contact_id INT PRIMARY KEY AUTO_INCREMENT,
                          name CHAR(100),
                          phone int(10),
                          email VARCHAR(50)
);

DROP DATABASE IF EXISTS touristguide;
CREATE DATABASE touristguide;
USE touristguide;

CREATE TABLE city (
    city_id INT AUTO_INCREMENT,
    city_name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (city_id)
);
DROP TABLE city;
CREATE TABLE attraction (
    attraction_id INT PRIMARY KEY AUTO_INCREMENT,
    attraction_name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(1000) NOT NULL,
    city_name VARCHAR(50) NOT NULL
);

Show TABLES;
CREATE TABLE tag (
    tag_id INT AUTO_INCREMENT,
    tag_name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (tag_id)
);

CREATE TABLE attraction_tag (
    attraction_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (attraction_id, tag_id),
    FOREIGN KEY (attraction_id)
        REFERENCES attraction (attraction_id),
    FOREIGN KEY (tag_id)
        REFERENCES tag (tag_id)
);

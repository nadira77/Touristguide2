DROP TABLE IF EXISTS attraction_tag;
DROP TABLE IF EXISTS attraction;
DROP TABLE IF EXISTS tag;

CREATE TABLE attraction (
                            attraction_id INT PRIMARY KEY AUTO_INCREMENT,
                            attraction_name VARCHAR(100) NOT NULL UNIQUE,
                            description VARCHAR(1000) NOT NULL,
                            city_name VARCHAR(50) NOT NULL
);

CREATE TABLE tag (
                     tag_id INT PRIMARY KEY AUTO_INCREMENT,
                     tag_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE attraction_tag (
                                attraction_id INT NOT NULL,
                                tag_id INT NOT NULL,
                                PRIMARY KEY (attraction_id, tag_id),
                                FOREIGN KEY (attraction_id) REFERENCES attraction(attraction_id),
                                FOREIGN KEY (tag_id) REFERENCES tag(tag_id)
);

INSERT INTO attraction (
    attraction_id, attraction_name, description, city_name
) VALUES
      (1, 'Tivoli',
       '19th-century amusement park with antique roller coaster & live entertainment program.',
       'Copenhagen'),
      (2, 'Rosenborg Castle',
       'Dutch Renaissance palace & gardens, including a museum housing the crown jewels, with guided tours.',
       'Copenhagen');
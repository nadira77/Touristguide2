USE
touristguide;

INSERT INTO city (city_name)
VALUES ('Copenhagen'),
       ('Hillerød');

INSERT INTO tag (tag_name)
VALUES ('KID_FRIENDLY'),
       ('FREE'),
       ('ART'),
       ('MUSEUM'),
       ('NATURE'),
       ('LANDMARK'),
       ('HISTORIC');

INSERT INTO attraction (attraction_name, description, city_id)
VALUES ('Tivoli', '19th-century amusement park with antique roller coaster & live entertainment program.', 1),
       ('Rosenborg Castle',
        'Dutch Renaissance palace & gardens, including a museum housing the crown jewels, with guided tours.', 1),
       ('Frederiksborg Castle',
        '17th-century Renaissance castle built on three small islands in a lake, home to the Museum of National History.',
        2),
       ('Nyhavn',
        'Copenhagen''s Nyhavn, or "New Harbor," is actually steeped in a long heritage. Colorful buildings line the canal and hint at a history of small-vessel traffic.',
        1),
       ('The Little Mermaid',
        'Bronze statue by Edvard Eriksen depicting a mermaid, based on the fairy tale by Hans Christian Andersen.', 1),
       ('Christiansborg Palace',
        'Seat of the Danish Parliament, the Supreme Court, and the Prime Minister''s office, with a royal reception hall.',
        1),
       ('The Round Tower',
        '17th-century tower with a spiral ramp leading to an observatory offering panoramic views of Copenhagen.', 1),
       ('Amalienborg Palace',
        'Winter home of the Danish royal family, consisting of four identical palace buildings around an octagonal courtyard.',
        1);


INSERT INTO attraction_tag (attraction_id, tag_id)
VALUES (1, 1),
       (1, 5),
       (2, 4),
       (2, 5),
       (3, 4),
       (3, 5),
       (4, 2),
       (4, 5),
       (5, 2),
       (5, 3),
       (6, 4),
       (7, 5),
       (8, 2);
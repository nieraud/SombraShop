INSERT INTO users
VALUES (DEFAULT,
        'Inna',
        'Rudenko',
        'Wed Sep 24 00:00:00 EEST 1997',
        380969635486,
        DEFAULT,
        'inusinka20@gmail.com',
        '$2a$12$L5K9h1B9bRFy6hfy29j7PuTl86xqonss5zpw628lJ3N04tcZZlw.i',
        '$2a$12$L5K9h1B9bRFy6hfy29j7Pu',
        DEFAULT);

CREATE TABLE test (
  id   UUID NOT NULL DEFAULT uuid_generate_v4(),
  date DATE          DEFAULT NULL
);

DELETE FROM admins
WHERE uniqueid = '3a33af2c-9349-4441-9f16-f17b6b27db73';




SELECT *
FROM categories;

UPDATE categories
SET (name, description)
= ('Accessories for laptops', 'This category contains accessories for laptops')
WHERE uniqueid = '5dde1350-2354-462d-aea5-4c77c3eae2b1';

INSERT INTO admins VALUES (DEFAULT, '0fcdbe32-11ac-438d-a763-041d1ffbcfd3', 1, 'Owner');

insert into blacklist VALUES (DEFAULT , 'e2bdf5ae-4378-49a0-8b01-cac45f745d0b', 'd8eabf81-c75b-4550-bb5c-70db3406719d', 'False user', DEFAULT );
insert into blacklist VALUES (DEFAULT , '4aced2af-69ae-4756-811c-776dc65e6ab7', 'd8eabf81-c75b-4550-bb5c-70db3406719d', 'False user', DEFAULT );



SELECT *
FROM users;
SELECT *
FROM admins;

SELECT *
FROM categories;

SELECT *
FROM subcategories;


SELECT *
FROM products;

SELECT *
FROM blacklist;


INSERT INTO
-- this file contains the sql code to create the database and the tables for
-- the inventory management system

drop DATABASE if EXISTS imsDb;

create DATABASE imsDb;

use imsDb;

CREATE table items(
    icode int PRIMARY key,
    title varchar(20) not null,
    packageDate date not null,
    fragile BOOLEAN default false,
    unit VARCHAR(10) not null,
    costPrice DECIMAL not null,
    sellingPrice DECIMAL not NULL
);


INSERT INTO items values
(1, "Pen Stand", "2020-02-01", false, "piece", 100, 200),
(2, "Marker", "2020-02-01", false, "Box of 10", 120, 220),
(3, "Catridge B/W", "2020-02-01", false, "piece", 500, 600);

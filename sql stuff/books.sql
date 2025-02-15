use wellsfargo;

drop table if exists Books;

create table Books(
    bcode INT PRIMARY KEY,
    title varchar(20) not NULL,
    pub_date date,
    price DECIMAL DEFAULT 100,
    edition int not null,
    author varchar(20) not NULL
);

-- describes table.
desc Books;

-- show all tables and databases
show tables;

show databases;

-- drop tables
drop table Books;

--- creating Books table after deleting it
create table Books(
    bcode INT PRIMARY KEY,
    title varchar(20) not NULL,
    pub_date date,
    price DECIMAL DEFAULT 100,
    edition int not null,
    author varchar(20) not NULL
);


-- Altering table
-- rename to new table name, a
-- dd colName colDef,
-- modify colName colDef,
-- drop colName,
-- change oldColName newCallName colDef)

-- when adding new column with not null
-- if there is already data available in table, we need to add a default value.
-- or we have to create new table with the new column and copy all the data over by providing new data for the new column.
alter table Books
    add category VARCHAR(10) not null;

-- the first clause tells to add this column at the beginning.
alter table Books
    modify category varchar(15) not null FIRST;


-- we are renaming the column name category to catg but we need to specify the constrains again.
-- the after clause tells to move the column after edition.
alter table Books
    change category catg varchar(20) not null after edition;

desc Books;

-- remove a column
alter table Books
    drop column edition;

-- insertion
insert into Books
values(101, "Let Us C", "2020-02-01", 435, "TEXT BOOK", "Yaswanth");

-- the following will not work
-- insert into Books
-- values(101, "Let Us C++", "2020-02-01", 435, "TEXT BOOK", "Yaswanth");


insert into Books
values(102, "Let Us C++", "2020-02-01", 535, "TEXT BOOK", "Yaswanth");


insert into Books(title, author, catg, bcode)
values("Java-HeadsFirst", "xyz", "TEXTBOOK", 103);


insert into Books(title, author, catg, bcode)
values("HTML5 Reference", "Vamsy", "TEXTBOOK", 104),
("CSS3 Reference", "Vamsy", "TEXTBOOK", 105),
("wings of fire", "Abdul Kalam", "AUTOBIOGRAPHY", 106);


-- select all columns
select * from Books;


-- update
update Books
set price = price * 0.5
where price > 600;


-- delete
delete from Books
where bcode = 103;

delete from Books
where price < 500;


drop table Books;

-- FK
create table Authors(
    aid int primary key AUTO_INCREMENT,
    title varchar(20),
    mailId varchar(20) UNIQUE
);

insert into Authors(title, mailId)
    values("Yaswanth", "yaswanth@gmail.com"),
    ("Abdul Kalam", "ak@gmail.com"),
    ("Zenith Smith", "zs@gmail.com");


create table Books(
    bcode INT PRIMARY KEY,
    title varchar(20) not NULL,
    price DECIMAL DEFAULT 250,
    aid int references authors(aid)
);

insert into Books
    values(101, "Let Us C", 345, 7);

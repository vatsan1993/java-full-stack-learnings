-- setup cd src/main/resources
-- sudo mysql -u root -p
-- source carEnquiry.sql

drop database if exists car_enquiry;
create DATABASE car_enquiry;

use car_enquiry;
-- note length() counts number of bytes, char_length() counts number of characters
create table enquiries(
    enquiry_id int primary key auto_increment,
    customer_name varchar(50) not null ,
    mobile BIGINT UNIQUE not null,
    vehicle_type ENUM('SUV', 'SEDAN', 'HATCHBACK') not null,
    budget_from BIGINT not null,
    budget_to BIGINT not null,
    enquiry_status ENUM('OPEN', 'CLOSED') DEFAULT 'OPEN',
    check (char_length(customer_name) between 3 and 25),
    CHECK (mobile REGEXP '^[0-9]{10}$')
);

insert into enquiries(customer_name, mobile, vehicle_type, budget_from, budget_to) values('John', 1234567890, 'SUV', 1000000, 2000000);
insert into enquiries(customer_name, mobile, vehicle_type, budget_from, budget_to) values('Jane', 1234567891, 'SEDAN', 500000, 1000000);
insert into enquiries(customer_name, mobile, vehicle_type, budget_from, budget_to) values('Jack', 1234567892, 'HATCHBACK', 200000, 500000);
insert into enquiries(customer_name, mobile, vehicle_type, budget_from, budget_to) values('Jill', 1234567893, 'SUV', 1000000, 2000000);
insert into enquiries(customer_name, mobile, vehicle_type, budget_from, budget_to) values('Jim', 1234567894, 'SEDAN', 500000, 1000000);
insert into enquiries(customer_name, mobile, vehicle_type, budget_from, budget_to) values('Jenny', 1234567895, 'HATCHBACK', 200000, 500000);



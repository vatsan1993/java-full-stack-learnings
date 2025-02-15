-- Set up before starting to run the project.
-- cd bin
-- mysql -u root -p
-- source employees.sql

use employees;


show tables;

desc titles;

desc employees;


desc salaries;

desc departments;

desc dept_emp;

desc dept_emp_latest_date;

desc current_dept_emp;

SELECT * from employees;

SELECT * from employees limit 10, 10;


select emp_no, first_name, hire_date from employees limit 0, 10;


-- between
select emp_no, first_name, hire_date from employees
where hire_date BETWEEN "1989-03-01" and "1989-03-31 "
limit 2501, 5;


-- like
select emp_no, first_name, hire_date from employees
where first_name like "A%" limit 0,20;


select first_name from employees
where first_name like "%z%" limit 0,20;


-- aggregate func
-- count sum avg min max
select count(*), min(hire_date), max(hire_date) from employees;


-- sub query

select emp_no, first_name, hire_date from employees
where hire_date = (select min(hire_date) from employees);

-- youngest employees
select emp_no, first_name, birth_date from employees
where birth_date = (select max(birth_date) from employees);


-- total number of employees recruited on first day of employment
select count(*) from employees
where hire_date = (select min(hire_date) from employees);


-- number of employees recruited on each day of employment
SELECT hire_date,count(*) from employees
GROUP BY hire_date;

-- where clause
SELECT emp_no, first_name, birth_date from employees
where birth_date <= "1986-03-01";

-- where clause then group by , having, order by

SELECT hire_date,count(*) from employees
where birth_date <= "1986-03-01"
GROUP BY hire_date;

-- having
SELECT hire_date,count(*) as num_emp from employees
where birth_date <= "1986-03-01"
GROUP BY hire_date
having num_emp >= 60;


-- order by

SELECT hire_date,count(*) as num_emp from employees
where birth_date <= "1960-03-01"
GROUP BY hire_date
having num_emp >= 60
ORDER BY hire_date DESC;

SELECT hire_date,count(*) as num_emp from employees
where birth_date <= "1960-03-01"
GROUP BY hire_date
having num_emp >= 60
ORDER BY num_emp ;


-- join current_dept_emp and departments
-- inner join,
    -- records available in both tables .
-- outer join
    -- left outer join - records available in left table bit not in right
    -- right outer join - records available in right table bit not in left
    -- full outer join -both left and right table data.
-- cross join
    -- cartesian product of two tables. each row of left table is joined with each row of right table.
-- self join
    -- join a table with itself.
desc departments;


desc current_dept_emp;

--  get employee number, department number and department name
SELECT cde.emp_no, cde.dept_no, d.dept_name
from current_dept_emp as cde INNER join departments as d
on cde.dept_no = d.dept_no;

--  get first_name, last_name,  department name

desc employees;
desc current_dept_emp;
DESC departments;

SELECT e.first_name, e.last_name, d.dept_name
from current_dept_emp as cde INNER join departments as d
on cde.dept_no = d.dept_no
INNER join employees as e
on cde.emp_no = e.emp_no ;

-- the following also works
SELECT e.first_name, e.last_name, d.dept_name
from current_dept_emp as cde INNER join departments as d inner join employees as e
on cde.dept_no = d.dept_no and cde.emp_no = e.emp_no ;

-- current manager employee_no of current manager for each department
-- hint: dept_no with latest dates.needs sub query and join
desc departments;
desc dept_manager;

-- step 1: get the latest from_date and to_date for each department
SELECT dept_no, max(to_date) as to_date , max(from_date) as from_date
        from dept_manager
        GROUP BY dept_no;

-- step 2: get the employee_no of the manager for each department
select d1.emp_no, d1.dept_no, d1.from_date, d2.to_date
    from dept_manager as d1 inner join (
        SELECT dept_no, max(to_date) as to_date , max(from_date) as from_date
        from dept_manager
        GROUP BY dept_no
    ) as d2
    on d1.dept_no = d2.dept_no and d1.to_date = d2.to_date and d1.from_date = d2.from_date

-- step 3: get the department name and manager_id
SELECT d.dept_name, dd.emp_no as manager_id
from departments as d INNER join(
    select d1.emp_no, d1.dept_no, d1.from_date, d2.to_date
    from dept_manager as d1 inner join (
        SELECT dept_no, max(to_date) as to_date , max(from_date) as from_date
        from dept_manager
        GROUP BY dept_no
    ) as d2
    on d1.dept_no = d2.dept_no and d1.to_date = d2.to_date and d1.from_date = d2.from_date
) as dd on d.dept_no = dd.dept_no;

-- this is same as above

select d.dept_name, dm.emp_no as manager_id from
departments as d INNER JOIN dept_manager as dm
on d.dept_no = dm.dept_no
where dm.to_date = (
    select max(to_date) from dept_manager as dm1 where dm1.dept_no = dm.dept_no
) and dm.from_date = (
    select max(from_date) from dept_manager as dm2 where dm2.dept_no = dm.dept_no
);


-- current manager employee_no , manager_name of current manager for each department
-- hint: dept_no with latest dates.needs sub query and join

SELECT
    d.dept_name,
    dm.emp_no AS manager_id,
    e.first_name,
    e.last_name
FROM departments AS d
INNER JOIN dept_manager AS dm
    ON d.dept_no = dm.dept_no
INNER JOIN employees AS e
    ON dm.emp_no = e.emp_no
WHERE (dm.dept_no, dm.to_date, dm.from_date) IN (
    SELECT dm_sub.dept_no, MAX(dm_sub.to_date), MAX(dm_sub.from_date)
    FROM dept_manager dm_sub
    GROUP BY dm_sub.dept_no
);



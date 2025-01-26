insert into person(id, name)
values(1001,'Marco');
insert into person(id, name)
values(1002,'Maya');


insert into student(id, name, creation_date, last_updated_date)
values(2001,'Mickey', now(), now());
insert into student(id, name, creation_date, last_updated_date)
values(2002,'Mini', now(), now());
insert into student(id, name, creation_date, last_updated_date)
values(2003,'Shikamaru', now(), now());

insert into course(id, name) values (3001, 'Spring boot in 100 steps');
insert into course(id, name) values (3002, 'Spring MVC in 250 steps');
insert into course(id, name) values (3003, 'Hibernet in 100 steps');
insert into course(id, name) values (3004, 'Cassandra in 50 steps');

insert into course_students (courses_id, students_id)
values(3001, 2001);
insert into course_students (courses_id, students_id)
values(3001, 2002);
insert into course_students (courses_id, students_id)
values(3002, 2002);
insert into course_students (courses_id, students_id)
values(3001, 2003);


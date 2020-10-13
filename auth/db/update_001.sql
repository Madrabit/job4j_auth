CREATE TABLE employee (
                          id SERIAL PRIMARY KEY NOT NULL,
                          name varchar(200),
                          inn integer,
                          hiringDate DATE DEFAULT CURRENT_DATE
)

INSERT INTO employee (name, inn) VALUES ('Директор', '222222');
INSERT INTO employee (name, inn) VALUES ('Рабочий', '333333');

create table person
(
    id       serial primary key not null,
    login    varchar(2000),
    password varchar(2000),
    employee_id integer REFERENCES employee(id)
);

insert into person (login, password, employee_id)
values ('parsentev', '123', 1);
insert into person (login, password, employee_id)
values ('ban', '123', 2);
insert into person (login, password, employee_id)
values ('ivan', '123', 2);



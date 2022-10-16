create table customer
(
    id         bigint primary key,
    first_name varchar(50) not null,
    last_name  varchar(50) not null
);
insert into customer (id, first_name, last_name)
values (1, 'Anakin', 'Skywalker'),
       (2, 'Lorne', 'Malvo'),
       (3, 'Thomas', 'Shelby');
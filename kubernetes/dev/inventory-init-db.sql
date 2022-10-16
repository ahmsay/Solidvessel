create table product
(
    id         serial primary key,
    name       varchar(50)   not null,
    price      numeric(5, 2) not null,
    category   varchar(50),
    payment_id bigint
);
insert into product (name, price, category, payment_id)
values ('Clipper', 2.5, 'Tool', 1),
       ('Laptop', 3, 'Electronics', 1),
       ('Phone', 5, 'Electronics', 1),
       ('Car', 200, 'Vehicle', 2),
       ('Spaceship', 500, 'Vehicle', 3),
       ('Apple', 499.99, 'Fruit', 3),
       ('Ice Pick', 53, 'Tool', null),
       ('Desk', 25, 'Furniture', null);
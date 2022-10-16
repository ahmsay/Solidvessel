create table payment
(
    id           serial primary key,
    total_charge numeric(10, 2) not null,
    customer_id  bigint         not null
);
insert into payment (total_charge, customer_id)
values (10.5, 1),
       (200, 2),
       (999.99, 2);

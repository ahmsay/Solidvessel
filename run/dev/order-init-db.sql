create table customer_order
(
    id          serial primary key,
    status      varchar(50) not null,
    customer_id bigint      not null,
    payment_id  bigint      not null
);
insert into customer_order (status, customer_id, payment_id)
values ('Delivered', 1, 1),
       ('On the way', 2, 2);

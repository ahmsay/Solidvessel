create table app_user
(
    id       serial primary key,
    username varchar(50) not null,
    password varchar(50) not null
);
insert into app_user (username, password)
values ('vader_666', 'i_hate_sand'),
       ('malvo_lrn', 'sioux_falls'),
       ('peaky_blinder', 'garrison_1918');
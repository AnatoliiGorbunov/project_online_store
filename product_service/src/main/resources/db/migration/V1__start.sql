create table if not exists products
(
    id
    bigserial
    primary
    key,
    title
    varchar
(
    255
),
    price int
    );

insert into products (title, price)
values ('Milk', 100),
       ('Bread', 50),
       ('Oil', 80),
       ('Orange', 70),
       ('Cheese', 90);
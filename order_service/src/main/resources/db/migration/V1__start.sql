create table orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_price int          not null,
    address     varchar,
    phone       varchar(255),
    created_at  timestamp,
    updated_at  timestamp
);

create table order_items
(
    id                bigserial primary key,
    title             varchar (255) not null,
    quantity          int    not null,
    order_id          bigint not null references orders (id),
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp,
    updated_at        timestamp
);

insert into orders (username, total_price, address, phone)
values ('bob', 200, 'address', '12345');

insert into order_items (title, order_id, quantity, price_per_product, price)
values ('milk', 1, 2, 100, 200);
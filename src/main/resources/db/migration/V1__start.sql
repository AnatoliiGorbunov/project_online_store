create table if not exists products
(
    id
    bigserial,
    title
    varchar
(
    255
), price int, primary key
(
    id
));
create table if not exists cart
(
    id
    bigserial,
    product_id
    bigint,
    title
    varchar
(
    255
));

INSERT INTO products (title, price)
VALUES ('bread', 40),
       ('Milk', 60),
       ('Tea', 53),
       ('coffee', 140),
       ('carrot', 40),
       ('potato', 44),
       ('orange', 90),
       ('banana', 87),
       ('kiwi', 133),
       ('chocolate', 73),
       ('juice', 100),
       ('apple', 82),
       ('mango', 170),
       ('beer', 60);

create table users
(
    id         bigserial,
    username   varchar(30) not null unique,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

create table roles
(
    id         bigserial,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob@gmail.com'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);


create table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    total_price int    not null,
    address     varchar,
    phone       varchar(255),
    created_at  timestamp,
    updated_at  timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    quantity          int    not null,
    order_id          bigint not null references orders (id),
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp,
    updated_at        timestamp
);



-- INSERT INTO users (name, fhone, email)
-- VALUES ('Jack', '89222456789','321@mail.ru'),
--        ('Mike', '89222346789','361@mail.ru'),
--        ('Mike', '89222556789','371@mail.ru'),
--        ('Mike', '89225676789','331@mail.ru'),
--        ('Mike', '89223426789','391@mail.ru'),
--        ('Mike', '89256736789','311@mail.ru'),
--        ('Mike', '89222346789','301@mail.ru');

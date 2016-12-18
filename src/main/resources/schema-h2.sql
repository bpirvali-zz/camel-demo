drop table if exists orders.orderItem;
drop table if exists orders.orders;
drop table if exists orders.catalogitem;
drop table if exists orders.customer;
drop schema if exists orders;


CREATE SCHEMA orders;
create table orders.customer (
        id bigserial not null,
        firstName text not null,
        lastName text not null,
        email text not null,
        primary key (id)
);

create table orders.catalogitem (
        id bigserial not null,
        itemNumber text not null,
        itemName text not null,
        itemType text not null,
        primary key (id)
) ;

create table orders.orders (
        id bigserial not null,
        customer_id bigint not null,
        orderNumber text not null,
        timeOrderPlaced timestamp not null,
        lastUpdate timestamp not null,
        status text not null,
        primary key (id)
) ;
alter table orders.orders add constraint orders_fk_1 foreign key (customer_id) references orders.customer (id);
create table orders.orderItem (
        id bigserial not null,
        order_id bigint not null,
        catalogitem_id bigint not null,
        status text not null,
        price decimal(20,5),
        lastUpdate timestamp not null,
        quantity integer not null,
        primary key (id)
) ;
alter table orders.orderItem add constraint orderItem_fk_1 foreign key (order_id) references orders.orders (id);
alter table orders.orderItem add constraint orderItem_fk_2 foreign key (catalogitem_id) references orders.catalogitem (id);

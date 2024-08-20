drop table if exists new_users;
create table new_users(
    identifier bigint primary key,
    email varchar(50) not null
);
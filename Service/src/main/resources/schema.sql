drop table if exists new_users;
create table new_users(
    identifier bigserial primary key,
    email varchar(50) not null
);
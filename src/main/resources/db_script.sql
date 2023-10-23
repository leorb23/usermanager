drop table if exists phones CASCADE 
drop table if exists users CASCADE 
create table phones (id binary not null, city_code bigint, country_code bigint, number bigint, user_id binary, primary key (id))
create table users (id binary not null, created date, email varchar(255), is_active boolean, last_login date, modified date, name varchar(255), password varchar(255), token varchar(255), primary key (id))
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table phones add constraint FKmg6d77tgqfen7n1g763nvsqe3 foreign key (user_id) references users
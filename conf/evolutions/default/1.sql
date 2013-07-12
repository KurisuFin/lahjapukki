# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table band (
  id                        bigint not null,
  name                      varchar(255),
  operator_id               bigint,
  constraint pk_band primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;


create table band_user (
  band_id                        bigint not null,
  user_id                        bigint not null,
  constraint pk_band_user primary key (band_id, user_id))
;
create sequence band_seq;

create sequence user_seq;

alter table band add constraint fk_band_operator_1 foreign key (operator_id) references user (id) on delete restrict on update restrict;
create index ix_band_operator_1 on band (operator_id);



alter table band_user add constraint fk_band_user_band_01 foreign key (band_id) references band (id) on delete restrict on update restrict;

alter table band_user add constraint fk_band_user_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists band;

drop table if exists band_user;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists band_seq;

drop sequence if exists user_seq;


# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table band (
  id                        bigint not null,
  name                      varchar(255),
  created                   timestamp,
  randomize                 timestamp,
  reveal                    timestamp,
  operator_id               bigint,
  constraint pk_band primary key (id))
;

create table participation (
  id                        bigint not null,
  band_id                   bigint,
  participant_id            bigint,
  constraint pk_participation primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create table wish (
  id                        bigint not null,
  participation_id          bigint,
  description               varchar(255),
  constraint pk_wish primary key (id))
;

create sequence band_seq;

create sequence participation_seq;

create sequence user_seq;

create sequence wish_seq;

alter table band add constraint fk_band_operator_1 foreign key (operator_id) references user (id) on delete restrict on update restrict;
create index ix_band_operator_1 on band (operator_id);
alter table participation add constraint fk_participation_band_2 foreign key (band_id) references band (id) on delete restrict on update restrict;
create index ix_participation_band_2 on participation (band_id);
alter table participation add constraint fk_participation_participant_3 foreign key (participant_id) references user (id) on delete restrict on update restrict;
create index ix_participation_participant_3 on participation (participant_id);
alter table wish add constraint fk_wish_participation_4 foreign key (participation_id) references participation (id) on delete restrict on update restrict;
create index ix_wish_participation_4 on wish (participation_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists band;

drop table if exists participation;

drop table if exists user;

drop table if exists wish;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists band_seq;

drop sequence if exists participation_seq;

drop sequence if exists user_seq;

drop sequence if exists wish_seq;


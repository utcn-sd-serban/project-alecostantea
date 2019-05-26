create table care_taker (id integer not null auto_increment, password varchar(255), username varchar(255), primary key (id)) engine=MyISAM
create table payment (id integer not null auto_increment, price integer, pet_id integer, room_id integer, user_id integer, primary key (id)) engine=MyISAM
create table pet (id integer not null auto_increment, name varchar(255), type_id integer, primary key (id)) engine=MyISAM
create table pet_type (id integer not null auto_increment, type varchar(255), supported_pets_id integer, compatible_pets_id integer, primary key (id)) engine=MyISAM
create table room (id integer not null auto_increment, primary key (id)) engine=MyISAM
alter table payment add constraint FKqbnqvdig9kfl1o61527ksudgg foreign key (pet_id) references pet (id)
alter table payment add constraint FKem9s82d20kd1u7lbp1pxgh5vs foreign key (room_id) references room (id)
alter table payment add constraint FK4spfnm9si9dowsatcqs5or42i foreign key (user_id) references user (id)
alter table pet add constraint FKrem5eg7ygof60lwmbo8gif3qg foreign key (type_id) references pet_type (id)
alter table pet_type add constraint FKq10mhf8yhuxn5aqva8gl3463y foreign key (supported_pets_id) references room (id)
alter table pet_type add constraint FKk4tbco0pioamslyacadag3x49 foreign key (compatible_pets_id) references care_taker (id)
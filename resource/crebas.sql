/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/4/12 8:11:17                            */
/*==============================================================*/


drop table if exists CommImage;

drop table if exists commodity;

drop table if exists orderItem;

drop table if exists orders;

drop index Relationship_2_FK on shoppingItem;

drop table if exists shoppingItem;

drop index Relationship_2_FK on user;

drop table if exists user;

/*==============================================================*/
/* Table: CommImage                                             */
/*==============================================================*/
create table CommImage
(
   id                   varchar(50) not null,
   cid                  varchar(50),
   primary key (id)
);

alter table CommImage comment 'CommImage';

/*==============================================================*/
/* Table: commodity                                             */
/*==============================================================*/
create table commodity
(
   cid                  varchar(50) not null,
   cname                varchar(50),
   price                float(8,2),
   repertory            int,
   description          varchar(50),
   type                 int,
   primary key (cid)
);

alter table commodity comment 'commodity';

/*==============================================================*/
/* Table: orderItem                                             */
/*==============================================================*/
create table orderItem
(
   cid                  varchar(50),
   oid                  varchar(50),
   number               varchar(50),
   itemPrice            float(8,2)
);

alter table orderItem comment 'orderItem';

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   oid                  varchar(50) not null,
   uid                  varchar(50),
   totalPrice           float(8,2),
   status               integer,
   telephone            varchar(50),
   address              varchar(50),
   orderTime            varchar(50),
   deliveryTime         varchar(50),
   discount             float(8,2),
   primary key (oid)
);

alter table orders comment 'orders';

/*==============================================================*/
/* Table: shoppingItem                                          */
/*==============================================================*/
create table shoppingItem
(
   cid                  varchar(50),
   uid                  varchar(50),
   status               bool,
   number               int,
   itemPrice            float(8,2)
);

alter table shoppingItem comment 'shoppingItem';

/*==============================================================*/
/* Index: Relationship_2_FK                                     */
/*==============================================================*/
create index Relationship_2_FK on shoppingItem
(
   
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   uid                  varchar(50) not null,
   name                 varchar(50),
   registrationDate     varchar(50),
   address              varchar(50),
   telephone            varchar(50),
   primary key (uid)
);

alter table user comment 'user';

/*==============================================================*/
/* Index: Relationship_2_FK                                     */
/*==============================================================*/
create index Relationship_2_FK on user
(
   
);

alter table CommImage add constraint FK_Relationship_4 foreign key (cid)
      references commodity (cid) on delete restrict on update restrict;

alter table orderItem add constraint FK_Relationship_5 foreign key (cid)
      references commodity (cid) on delete restrict on update restrict;

alter table orderItem add constraint FK_Relationship_6 foreign key (oid)
      references orders (oid) on delete restrict on update restrict;

alter table orders add constraint FK_Relationship_1 foreign key (uid)
      references user (uid) on delete restrict on update restrict;

alter table shoppingItem add constraint FK_Relationship_2 foreign key (uid)
      references user (uid) on delete restrict on update restrict;

alter table shoppingItem add constraint FK_Relationship_3 foreign key (cid)
      references commodity (cid) on delete restrict on update restrict;


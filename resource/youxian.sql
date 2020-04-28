/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/4/28 ÐÇÆÚ¶þ ÏÂÎç 21:53:01                    */
/*==============================================================*/


drop table if exists CommImage;

drop table if exists commodity;

drop table if exists orderItem;

drop table if exists orders;

drop table if exists shippingAddress;

drop index Relationship_2_FK on shoppingItem;

drop table if exists shoppingItem;

drop index Relationship_2_FK on user;

drop table if exists user;

/*==============================================================*/
/* Table: CommImage                                             */
/*==============================================================*/
create table CommImage
(
   id                   int not null,
   cid                  varchar(50) not null,
   primary key (id)
);

alter table CommImage comment 'CommImage';

/*==============================================================*/
/* Table: commodity                                             */
/*==============================================================*/
create table commodity
(
   cid                  varchar(50) not null,
   cname                varchar(50) not null,
   price                float(8,2) not null,
   repertory            int not null,
   description          varchar(50) not null,
   type                 int not null,
   primary key (cid)
);

alter table commodity comment 'commodity';

/*==============================================================*/
/* Table: orderItem                                             */
/*==============================================================*/
create table orderItem
(
   id                   int not null auto_increment,
   cid                  varchar(50) not null,
   oid                  varchar(50) not null,
   number               int not null,
   itemPrice            float(8,2) not null,
   primary key (id)
);

alter table orderItem comment 'orderItem';

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   oid                  varchar(50) not null,
   uid                  varchar(50) not null,
   totalPrice           float(8,2) not null,
   status               integer not null,
   orderTime            varchar(50) not null,
   deliveryTime         varchar(50) not null,
   discount             float(8,2) not null,
   primary key (oid)
);

alter table orders comment 'orders';

/*==============================================================*/
/* Table: shippingAddress                                       */
/*==============================================================*/
create table shippingAddress
(
   sid                  varchar(50) not null,
   uid                  varchar(50) not null,
   name                 varchar(50) not null,
   telephone            varchar(50) not null,
   address              varchar(50) not null,
   primary key (sid)
);

alter table shippingAddress comment 'shippingAddress';

/*==============================================================*/
/* Table: shoppingItem                                          */
/*==============================================================*/
create table shoppingItem
(
   id                   int not null auto_increment,
   cid                  varchar(50) not null,
   uid                  varchar(50) not null,
   status               bool not null,
   number               int not null,
   itemPrice            float(8,2) not null,
   primary key (id)
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
   registrationDate     varchar(50) not null,
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

alter table shippingAddress add constraint FK_Reference_7 foreign key (uid)
      references user (uid) on delete restrict on update restrict;

alter table shoppingItem add constraint FK_Relationship_2 foreign key (uid)
      references user (uid) on delete restrict on update restrict;

alter table shoppingItem add constraint FK_Relationship_3 foreign key (cid)
      references commodity (cid) on delete restrict on update restrict;


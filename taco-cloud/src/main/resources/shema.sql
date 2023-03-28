create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists Taco (
    id identity ,
    name varchar(50) not null,
    createdAt timestamp not null
);

create table if not exists Taco_Ingredients (
    taco bigint not null,
    ingredient varchar(4) not null
);

alter table Taco_Ingredients add foreign key (taco) references Taco (id);
alter table Taco_Ingredients add foreign key (ingredient) references Ingredient (id);

create table if not exists Taco_Order (
    id identity,
    deliveryName varchr(50) not null,
    deliveryStreet varchr(50) not null,
    deliveryCity varchr(50) not null,
    deliveryState varchr(2) not null,
    deliveryZip varchr(10) not null,
    ccNumber varchr(16) not null,
    ccExpiration varchr(5) not null,
    ccCVV varchr(3) not null,
    placedAt timestamp not null
);

create table if not exists Taco_Order_Tacos (
    tacoOrder bigint not null,
    taco bigint not null
);

alter table Taco_Order_Tacos add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos add foreign key (taco) references Taco(id);

delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

insert into Ingredient(id, name, type) values('FLTO','Flour Tortilla','WRAP');
insert into Ingredient(id, name, type) values('COTO','Corn Tortilla','WRAP');
insert into Ingredient(id, name, type) values('GRBF','Ground Beef','PROTEIN');
insert into Ingredient(id, name, type) values('CARN','Carnitas','PROTEIN');
insert into Ingredient(id, name, type) values('TMTO','Diced Tomatoes','VEGGIES');
insert into Ingredient(id, name, type) values('LETC','Lettuce','VEGGIES');
insert into Ingredient(id, name, type) values('CHED','Cheddar','CHEESE');
insert into Ingredient(id, name, type) values('JACK','Monterrey Jack','CHEESE');
insert into Ingredient(id, name, type) values('SLSA','Salsa','SAUCE');
insert into Ingredient(id, name, type) values('SRCR','Sour Cream','SAUCE');
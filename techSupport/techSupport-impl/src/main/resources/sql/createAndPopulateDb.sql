
-- START SNIPPET: e1

create table "TEST".ACCOUNT (
	ID BIGINT default GENERATED_BY_DEFAULT not null primary key,
	ACTIVE SMALLINT not null,
	CREATEDAT TIMESTAMP not null,
	EMAIL VARCHAR(255) not null,
	NAME VARCHAR(40) not null,
	PASSWORD VARCHAR(64) not null,
	SALT VARCHAR(64) not null
);

create table "TEST".ITEM (
	ID BIGINT default GENERATED_BY_DEFAULT not null primary key,
	NAME VARCHAR(255)
);

create table "TEST".ORDER_ITEM (
	ORDER_ID BIGINT not null,
	ITEMS_ID BIGINT not null
);

-- insert into ACCOUNT values (1, 'Camel', 'ASF');
-- END SNIPPET: e1
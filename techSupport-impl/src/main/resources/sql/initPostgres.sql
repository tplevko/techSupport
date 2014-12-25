-- ####################################### --
-- ##  insert into pg some example data ## --
-- ####################################### --
-- ####################################### --
-- ####### accounts ###################### --
-- ####################################### --

INSERT INTO "public".account (id, active, createdat, email, firstname, lastname, password, salt) 
	VALUES (20000, true, '2014-12-24 12:09:48.032', 'aaa@aaa.aaa', 'aaa', 'aaa', '2c56674e4bf618de3dace05e35e5d0c630be88901bb6a601228bd7342dc8ee19', '3378813711555038275');
INSERT INTO "public".account (id, active, createdat, email, firstname, lastname, password, salt) 
	VALUES (20001, true, '2014-12-24 12:10:50.821', 'siradolan@seznam.cz', 'siradolan', 'siradolan', '8c6b0b9e09b779f5f521a4fe374aad42d0f6e4b29335dc26b4bdd8e35b7ef4ac', '1462448868146282716');

-- ####################################### --
-- ####################################### --
-- ####### customers ##################### --
-- ####################################### --

INSERT INTO "public".customer (id) 
	VALUES (20001);

-- ####################################### --
-- ####################################### --
-- ####### employees ##################### --
-- ####################################### --

INSERT INTO "public".employee (isadmin, id) 
	VALUES (false, 20000);

-- ####################################### --
-- ####################################### --
-- ####### products ###################### --
-- ####################################### --

INSERT INTO "public".product (id, defaultpriority, "name") 
	VALUES (1, 1, 'windows');
INSERT INTO "public".product (id, defaultpriority, "name") 
	VALUES (2, 2, 'linux');
INSERT INTO "public".product (id, defaultpriority, "name") 
	VALUES (3, 5, 'mac');
INSERT INTO "public".product (id, defaultpriority, "name") 
	VALUES (4, 10, 'workstation');

-- ####################################### --
-- ####################################### --
-- ####### requests ###################### --
-- ####################################### --

INSERT INTO "public".request (id, created, executed, finished, priority, requesttitle, text, assignee_id, owner_id, product_id) 
	VALUES (5, '2014-12-24 12:15:01.781', true, '2014-12-24 12:17:50.138', 10, 'I need assistance with my workstation', '23571', 20000, 20001, 4);

-- ####################################### --
-- ####################################### --
-- ####### comments ###################### --
-- ####################################### --

INSERT INTO "public".comments (id, created, text, commenter_id, request_id) 
	VALUES (6, '2014-12-24 12:15:47.565', '23568', 20001, 5);
INSERT INTO "public".comments (id, created, text, commenter_id, request_id) 
	VALUES (7, '2014-12-24 12:17:08.785', '23569', 20000, 5);
INSERT INTO "public".comments (id, created, text, commenter_id, request_id) 
	VALUES (8, '2014-12-24 12:17:44.844', '23570', 20000, 5);

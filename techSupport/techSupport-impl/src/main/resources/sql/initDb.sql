-- -- initialize DB snippet
-- --
--  
-- -- ####################################################################### --
-- -- ####################################################################### --
-- -- #################      CUSTOMERS        ############################### --
-- -- ####################################################################### --
-- -- ####################################################################### --
-- 
-- 
-- INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (5, 0, '2014-04-26 18:07:09.205', 'aaa@aaa.aaa', 'aaa', 'aaa', 'e5f8726019dfc64bb7516813b51a921e333f71e26f430643c45bb60646da5160', '7397143386588948356');
-- INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (6, 0, '2014-04-26 18:07:15.99', 'bbb@bbb.bbb', 'bbb', 'bbb', '4741f6a8ed17ae8dddc6232990f06152e7acf9af440b0c9c5bee28f36741bae5', '7363590351492643490');
-- INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (7, 0, '2014-04-26 18:07:23.382', 'ccc@ccc.ccc', 'ccc', 'ccc', '98eceb92789669d9f52a776a80501f7d5e2ecaa1de5da16830fc703679f54e23', '10463568667319912444');
-- INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (8, 0, '2014-08-30 18:09:56.719', 'sss', 'sss', 'sss', 'a4cf0943ad46ef049df4ead28ce9c9c3f859a1feea9f5548c30f932ece65e4c2', '9959135328723566996');
-- INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (9, 0, '2014-09-05 20:38:20.92', 'aaaa@aaa.aa', 'aaaa', 'aaaa', '12826380aab9e5ad5e29cfcf261045021eea140f0aff6ef0fb7baab7c84f5c09', '4516536483104047301');

-- ####################################################################### --
-- ####################################################################### --
-- #################      EMPLOYEES        ############################### --
-- ####################################################################### --
-- ####################################################################### --

INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
	VALUES (2, 0, '2014-04-26 18:12:18.182', 'ddd', 'ddd', 'ddd', 'cc5fc5ecc32fcfbc56f9515e51092b803a3b4989888cdd2ce7b3a87c507597af', '13119196590817088758', 0);
INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
	VALUES (3, 1, '2014-04-26 18:12:32.274', 'eee', 'eee', 'eee', '3249e934b9478051f05907d5cf20478bba9f21bd246286dee4058a1c10da10fb', '15753774739054630467', 0);
INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
	VALUES (4, 1, '2014-04-26 18:12:41.674', 'fff', 'fff', 'fff', '96b667f7f1b612f55eb87c0a0627c1d65d34ceb62fce10d5189441e0a9dbd05d', '2990320135703869779', 0);

-- ####################################################################### --
-- ####################################################################### --
-- #################      PRODUCTS         ############################### --
-- ####################################################################### --
-- ####################################################################### --

INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
	VALUES (2, 'CLI consolle');
INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
	VALUES (2, 'management console');
INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
	VALUES (1, 'web server');
INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
	VALUES (8, 'integration tooling');

-- 
-- -- ####################################################################### --
-- -- ####################################################################### --
-- -- #################      REQUESTS         ############################### --
-- -- ####################################################################### --
-- -- ####################################################################### --
-- 
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2014-09-06 10:55:20.769', 0, NULL, 2, 'aaa', 'aaa', NULL, 5, 1);
-- 
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2014-08-26 18:12:32.274', 1, NULL, 1, 'My stupid, stupid question...', 'Hi, just a quick question : how are you guys today?', 2, 7, 3);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2011-04-26 18:07:09.205', 1, '2011-04-26 18:17:09.205', 2, 'Reminder, to not to fall assleep',
-- 'hi, 
-- how are you guys today? 
-- I need a help with the issye 14X...
-- it is still not working as expected...
-- let me know, if you are going to fix it ASAP
-- 
-- Thanks,
-- Tomas Plevko', NULL, 6, 1);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2014-05-23 12:07:09.205', 0, '2014-06-26 18:17:09.205', 2,'sss', 'asdasd', NULL, 5, 1);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2012-04-23 19:09:09.205', 1, '2013-04-26 18:07:09.205', 2,'asdasd', 'asdasdasdasdads', 4, 5, 2);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2011-01-23 10:01:09.205', 1, '2012-04-26 18:07:09.205', 2, 'Need a quic info on issue 143',
-- 'Hi you guys,
-- 
-- I need ASAP a update on issue 143.
-- There is a big bussines need for us, to get info on this,
-- because, we can not continue in creating new infrastructure,
-- at this time.
-- 
-- Thanks in advance for your answers.
-- 
-- Regards,
-- Alfons Marina', 3, 5, 2);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2001-02-23 19:01:07.205', 1, '2002-04-26 18:07:09.205', 2, 'oksds','asd', 3, 5, 1);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2002-09-23 19:01:07.205', 0, NULL, 1, 'im pissed', 'Hi,
-- I would like to ask, how it is possible, that it does not work... 
-- sincerelly, fuck you', NULL, 5, 3);
-- 
-- -- ####################################################################### --
-- -- ####################################################################### --
-- -- #################      COMMENTS         ############################### --
-- -- ####################################################################### --
-- -- ####################################################################### --
-- 
-- INSERT INTO TEST.COMMENTS (CREATED, TEXT, COMMENTER_ID, REQUEST_ID) 
-- 	VALUES ('2014-07-12 14:18:08.527', 'ahoj, mam nejake otazky ohladne tohoto issue, kontaktujte ma prosim co najskor...', 5, 1);
-- INSERT INTO TEST.COMMENTS (CREATED, TEXT, COMMENTER_ID, REQUEST_ID) 
-- 	VALUES ('2014-07-17 14:19:08.527', 'ahoj, este stale ste ma nekontaktovali, uz mam kurva nervy...', 5, 1);
-- INSERT INTO TEST.COMMENTS (CREATED, TEXT, COMMENTER_ID, REQUEST_ID) 
-- 	VALUES ('2014-10-15 14:18:08.527', 'vy skurvene svine, uz ma kontaktujte okamzite...', 5, 1);
-- INSERT INTO TEST.COMMENTS (CREATED, TEXT, COMMENTER_ID, REQUEST_ID) 
-- 	VALUES ('2014-10-12 14:18:08.527', 'Ahoj, mam taky jeden dotaz... ktorou rukou si mam utierat zadok?', 5, 5);
-- INSERT INTO TEST.COMMENTS (CREATED, TEXT, COMMENTER_ID, REQUEST_ID) 
-- 	VALUES ('2014-08-9 14:18:08.527', 'Dnes je krasny den.', 5, 4);
-- INSERT INTO TEST.COMMENTS (CREATED, TEXT, COMMENTER_ID, REQUEST_ID) 
-- 	VALUES ('2014-10-19 14:18:08.527', 'Male zelene myslienky zurivo spia', 4, 4);
-- INSERT INTO TEST.COMMENTS (CREATED,TEXT, COMMENTER_ID, REQUEST_ID) 
-- 	VALUES ('2014-05-19 14:18:08.527', 'Zabradlie stolicka mudra zalezitost preto', 5, 4);
-- INSERT INTO TEST.COMMENTS (CREATED, TEXT, COMMENTER_ID, REQUEST_ID) 
-- 	VALUES ('2014-11-19 14:18:08.527', 'aoskd', 5, 1);

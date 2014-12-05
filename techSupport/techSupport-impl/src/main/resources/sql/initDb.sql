-- initialize DB snippet
--
--  
-- INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (2000000, 0, '2014-04-26 18:07:09.205', 'aaa@aaa.aaa', 'aaa', 'aaa', 'e5f8726019dfc64bb7516813b51a921e333f71e26f430643c45bb60646da5160', '7397143386588948356');
-- INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (2000001, 0, '2014-04-26 18:07:15.99', 'bbb@bbb.bbb', 'bbb', 'bbb', '4741f6a8ed17ae8dddc6232990f06152e7acf9af440b0c9c5bee28f36741bae5', '7363590351492643490');
-- INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (2000002, 0, '2014-04-26 18:07:23.382', 'ccc@ccc.ccc', 'ccc', 'ccc', '98eceb92789669d9f52a776a80501f7d5e2ecaa1de5da16830fc703679f54e23', '10463568667319912444');
-- INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (2000003, 0, '2014-08-30 18:09:56.719', 'sss', 'sss', 'sss', 'a4cf0943ad46ef049df4ead28ce9c9c3f859a1feea9f5548c30f932ece65e4c2', '9959135328723566996');
-- INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (2000004, 0, '2014-09-05 20:38:20.92', 'aaaa@aaa.aa', 'aaaa', 'aaaa', '12826380aab9e5ad5e29cfcf261045021eea140f0aff6ef0fb7baab7c84f5c09', '4516536483104047301');
-- 
-- 
-- -- INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
-- -- 	VALUES (2, 0, '2014-04-26 18:12:18.182', 'ddd', 'ddd', 'ddd', 'cc5fc5ecc32fcfbc56f9515e51092b803a3b4989888cdd2ce7b3a87c507597af', '13119196590817088758', 0);
-- INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
-- 	VALUES (1000000, 1, '2014-04-26 18:12:32.274', 'eee', 'eee', 'eee', '3249e934b9478051f05907d5cf20478bba9f21bd246286dee4058a1c10da10fb', '15753774739054630467', 0);
-- INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
-- 	VALUES (1000001, 1, '2014-04-26 18:12:41.674', 'fff', 'fff', 'fff', '96b667f7f1b612f55eb87c0a0627c1d65d34ceb62fce10d5189441e0a9dbd05d', '2990320135703869779', 0);
-- 
-- INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME")
-- 	VALUES (2, 'asd');
-- INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
-- 	VALUES (2, 'llllll');
-- INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
-- 	VALUES (1, 'qqqqqqqqq');
-- INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME")
-- 	VALUES (8, 'oiiiiiiiiiii');
-- 
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2014-09-06 10:55:20.769', 0, NULL, 2, 'aaa', 'aaa', NULL, 2000001, 1);
-- 
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2012-04-23 19:09:09.205', 1, '2013-04-26 18:07:09.205', 2,'asdasd', 'asdasdasdasdads', 1000000, 2000003, 2);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2011-01-23 10:01:09.205', 1, '2012-04-26 18:07:09.205', 2, 'kkkk','asdasdasd', 1000001, 2000004, 2);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2001-02-23 19:01:07.205', 1, '2002-04-26 18:07:09.205', 2, 'oksds','asd', 1000001, 2000005, 1);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
-- 	VALUES ('2002-09-23 19:01:07.205', 0, NULL, 1, 'im pissed', 'Hi,
-- I would like to ask, how it is possible, that it does not work... 
-- sincerelly, fuck you', NULL, 5, 3);


-- INSERT INTO TEST.ACCOUNT (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
-- 	VALUES (1, 1, '2014-10-29 19:55:24.589', 'admin', 'admin', 'admin', '55d9a18de07f4deb1af9ac249cd943530377ed19054e22e371e408154b71ade9', '13499738212795156510');
INSERT INTO TEST.ACCOUNT (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (20000, 0, '2014-10-29 19:55:36.437', 'asd', 'asd', 'asd', '7e49ac9c4bc59792bd8a7337b64aea7c5936183d7aaf1346ca359482650d028a', '7443152377613752316');
INSERT INTO TEST.ACCOUNT (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (20001, 0, '2014-10-29 19:55:48.078', 'asdasd', 'sadasd', 'asdasd', 'ad1d7761e08959e5d94de0a1ded5c23489f9861161dfc6af3a8e43289be24b0d', '4174273705606333236');
INSERT INTO TEST.ACCOUNT (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (20002, 0, '2014-10-29 19:56:16.505', 'kkk@kkk.kkk', 'kkk', 'kkk', 'b2a09b107c08f80c21f7c66be86d8128cd2998b8c90d3bce2ce2d5c2be18df54', '15744971731999894182');
INSERT INTO TEST.ACCOUNT (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (20003, 0, '2014-10-29 19:56:40.113', 'kok@kok.kok', 'kok', 'kok', '9db69d7025fe266394c25ff9c589588efcc2fdf74612731950d1a32e25e8db99', '14384948383535239055');
INSERT INTO TEST.ACCOUNT (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (20004, 0, '2014-10-29 19:57:05.641', 'kkkk', 'kkkk', 'kkkk', '105cefc5435616518e99c10d6c21319d5952f130edf6cde8f0990a661bcbd76a', '9490426633088268156');
INSERT INTO TEST.ACCOUNT (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (20005, 0, '2014-10-29 19:57:58.315', 'kak@kak.kak', 'kak', 'kak', 'db85418c3435f7eeda16dad8b02826cde4bd5cbe68dc989850f0ed737ea3aa39', '15350256415764245185');

-- ####################### --
-- the password is : lolo --
INSERT INTO TEST.ACCOUNT (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (20006, 0, '2014-12-05 08:28:44.835', 'siradolan@gmail.com', 'lolo', 'lolovic', '721020927e1658aa2694590ec0dd14e9cd802ef79e66ee96a5cb3555df838415', '5684782791449779940');
-- ####################### --
-- ####################### --

INSERT INTO TEST.CUSTOMER (ID) 
	VALUES (20000);
INSERT INTO TEST.CUSTOMER (ID) 
	VALUES (20001);
INSERT INTO TEST.CUSTOMER (ID) 
	VALUES (20002);
INSERT INTO TEST.CUSTOMER (ID) 
	VALUES (20006);

-- INSERT INTO TEST.EMPLOYEE (ISADMIN, ID) 
-- 	VALUES (1, 1);
INSERT INTO TEST.EMPLOYEE (ISADMIN, ID) 
	VALUES (0, 20003);
INSERT INTO TEST.EMPLOYEE (ISADMIN, ID) 
	VALUES (0, 20004);
INSERT INTO TEST.EMPLOYEE (ISADMIN, ID) 
	VALUES (0, 20005);


INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME")
	VALUES (2, 'asd');
INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
	VALUES (2, 'llllll');
INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
	VALUES (1, 'qqqqqqqqq');
INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME")
	VALUES (8, 'oiiiiiiiiiii');

INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
	VALUES ('2014-08-26 18:12:32.274', 1, NULL, 1, '=vasij', 'okok', NULL, 20000, 3);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
	VALUES ('2011-04-26 18:07:09.205', 1, '2011-04-26 18:17:09.205', 2, 'sdasd','aaa', NULL, 20002, 1);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, REQUESTTITLE, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
	VALUES ('2014-05-23 12:07:09.205', 0, '2014-06-26 18:17:09.205', 2,'sss', 'asdasd', NULL, 20001, 1);

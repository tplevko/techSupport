-- initialize DB snippet
--
 
INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (5, 0, '2014-04-26 18:07:09.205', 'aaa', 'aaa', 'aaa', 'e5f8726019dfc64bb7516813b51a921e333f71e26f430643c45bb60646da5160', '7397143386588948356');
INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (6, 0, '2014-04-26 18:07:15.99', 'bbb', 'bbb', 'bbb', '4741f6a8ed17ae8dddc6232990f06152e7acf9af440b0c9c5bee28f36741bae5', '7363590351492643490');
INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (7, 0, '2014-04-26 18:07:23.382', 'ccc', 'ccc', 'ccc', '98eceb92789669d9f52a776a80501f7d5e2ecaa1de5da16830fc703679f54e23', '10463568667319912444');

INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
	VALUES (2, 0, '2014-04-26 18:12:18.182', 'ddd', 'ddd', 'ddd', 'cc5fc5ecc32fcfbc56f9515e51092b803a3b4989888cdd2ce7b3a87c507597af', '13119196590817088758', 0);
INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
	VALUES (3, 1, '2014-04-26 18:12:32.274', 'eee', 'eee', 'eee', '3249e934b9478051f05907d5cf20478bba9f21bd246286dee4058a1c10da10fb', '15753774739054630467', 0);
INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
	VALUES (4, 1, '2014-04-26 18:12:41.674', 'fff', 'fff', 'fff', '96b667f7f1b612f55eb87c0a0627c1d65d34ceb62fce10d5189441e0a9dbd05d', '2990320135703869779', 0);

INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
	VALUES (2, 'asd');
INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
	VALUES (2, 'llllll');
INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
	VALUES (1, 'qqqqqqqqq');
INSERT INTO TEST.PRODUCT (DEFAULTPRIORITY, "NAME") 
	VALUES (8, 'oiiiiiiiiiii');

INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
	VALUES ('2014-08-26 18:12:32.274', 1, '2014-04-26 20:07:09.205', 1, 'okok', 2, 7, 3);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
	VALUES ('2011-04-26 18:07:09.205', 1, '2011-04-26 18:17:09.205', 2, 'sdasd', NULL, 6, 1);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
	VALUES ('2014-05-23 12:07:09.205', 0, '2014-06-26 18:17:09.205', 2, 'asdasd', NULL, 5, 1);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
	VALUES ('2012-04-23 19:09:09.205', 1, '2013-04-26 18:07:09.205', 2, 'asdasdasdasdads', 4, 5, 2);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
	VALUES ('2011-01-23 10:01:09.205', 1, '2012-04-26 18:07:09.205', 2, 'asdasdasd', 3, 5, 2);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
	VALUES ('2001-02-23 19:01:07.205', 1, '2002-04-26 18:07:09.205', 2, 'asd', 3, 5, 1);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID, PRODUCT_ID) 
	VALUES ('2002-09-23 19:01:07.205', 0, NULL, 1, 'Hi,
I would like to ask, how it is possible, that it does not work... 
sincerelly, fuck you', NULL, 5, 3);


-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID) 
-- 	VALUES ('2014-04-26', 0, NULL, NULL, 'hi man', NULL, NULL);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID) 
-- 	VALUES ('2014-04-26', 0, NULL, NULL, 'yolo man', NULL, NULL);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID) 
-- 	VALUES ('2014-04-26', 0, NULL, NULL, 'asdf man', NULL, NULL);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID) 
-- 	VALUES ('2014-04-26', 0, NULL, NULL, 'hahahahaha', NULL, NULL);
-- INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID) 
-- 	VALUES ('2014-04-26', 0, NULL, NULL, 'lalalala tralalalala', NULL, NULL);


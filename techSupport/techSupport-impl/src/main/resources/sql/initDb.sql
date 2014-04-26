-- initialize DB snippet

-- init users 
-- 
-- INSERT INTO TEST.ACCOUNT (ACTIVE, CREATEDAT, EMAIL, ISADMIN, "NAME", PASSWORD, SALT) 
-- 	VALUES (1, '2014-04-13 15:57:24.874', 'tomas', 1, 'tomas', 'c0cecb2275546c2c500cbe83be48b91b4a453a855e66adbf6711b5fd8345c051', '4991934116191320543');
-- INSERT INTO TEST.ACCOUNT (ACTIVE, CREATEDAT, EMAIL, ISADMIN, "NAME", PASSWORD, SALT) 
-- 	VALUES (1, '2014-04-13 15:57:34.197', 'asdf', 0, 'asdf', '19f5307a2aa5e93c6f4233d94e45e2a9898a458f5e8d1917959d75a5f5d985bc', '12408627716164010523');
-- INSERT INTO TEST.ACCOUNT (ACTIVE, CREATEDAT, EMAIL, ISADMIN, "NAME", PASSWORD, SALT) 
-- 	VALUES (0, '2014-04-13 15:57:41.878', 'aaaa', 0, 'aaaa', '5558a6228ae8da946ed1bec443c21bd49e8ab6e6e837f03f82893568d34a8c8c', '18175087180324813479');
-- 
-- -- init items 
-- 
-- INSERT INTO TEST.ITEM ("NAME", PRICE) 
-- 	VALUES ('item nr. 1', 15.00);
-- INSERT INTO TEST.ITEM ("NAME", PRICE) 
-- 	VALUES ('item nr. 2', 30.00);
-- INSERT INTO TEST.ITEM ("NAME", PRICE) 
-- 	VALUES ('item nr. 5', 89.00);
-- INSERT INTO TEST.ITEM ("NAME", PRICE) 
-- 	VALUES ('item nr. 10', 12.00);
-- INSERT INTO TEST.ITEM ("NAME", PRICE) 
-- 	VALUES ('item nr. 15', 1.00);

INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (2, 0, '2014-04-26 18:07:09.205', 'aaa', 'aaa', 'aaa', 'e5f8726019dfc64bb7516813b51a921e333f71e26f430643c45bb60646da5160', '7397143386588948356');
INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (3, 0, '2014-04-26 18:07:15.99', 'bbb', 'bbb', 'bbb', '4741f6a8ed17ae8dddc6232990f06152e7acf9af440b0c9c5bee28f36741bae5', '7363590351492643490');
INSERT INTO TEST.CUSTOMER (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT) 
	VALUES (4, 0, '2014-04-26 18:07:23.382', 'ccc', 'ccc', 'ccc', '98eceb92789669d9f52a776a80501f7d5e2ecaa1de5da16830fc703679f54e23', '10463568667319912444');

INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
	VALUES (2, 0, '2014-04-26 18:12:18.182', 'ddd', 'ddd', 'ddd', 'cc5fc5ecc32fcfbc56f9515e51092b803a3b4989888cdd2ce7b3a87c507597af', '13119196590817088758', 0);
INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
	VALUES (3, 1, '2014-04-26 18:12:32.274', 'eee', 'eee', 'eee', '3249e934b9478051f05907d5cf20478bba9f21bd246286dee4058a1c10da10fb', '15753774739054630467', 0);
INSERT INTO TEST.EMPLOYEE (ID, ACTIVE, CREATEDAT, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, SALT, ISADMIN) 
	VALUES (4, 1, '2014-04-26 18:12:41.674', 'fff', 'fff', 'fff', '96b667f7f1b612f55eb87c0a0627c1d65d34ceb62fce10d5189441e0a9dbd05d', '2990320135703869779', 0);


INSERT INTO TEST.PRODUCT ("NAME") 
	VALUES ('abc');
INSERT INTO TEST.PRODUCT ("NAME") 
	VALUES ('def');
INSERT INTO TEST.PRODUCT ("NAME") 
	VALUES ('ghi');
INSERT INTO TEST.PRODUCT ("NAME") 
	VALUES ('jkl');
INSERT INTO TEST.PRODUCT ("NAME") 
	VALUES ('mno');

INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID) 
	VALUES ('2014-04-26', 0, NULL, NULL, 'hi man', NULL, NULL);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID) 
	VALUES ('2014-04-26', 0, NULL, NULL, 'yolo man', NULL, NULL);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID) 
	VALUES ('2014-04-26', 0, NULL, NULL, 'asdf man', NULL, NULL);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID) 
	VALUES ('2014-04-26', 0, NULL, NULL, 'hahahahaha', NULL, NULL);
INSERT INTO TEST.REQUEST (CREATED, EXECUTED, FINISHED, PRIORITY, TEXT, ASSIGNEE_ID, OWNER_ID) 
	VALUES ('2014-04-26', 0, NULL, NULL, 'lalalala tralalalala', NULL, NULL);

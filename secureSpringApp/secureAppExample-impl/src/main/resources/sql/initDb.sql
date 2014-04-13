-- initialize DB snippet

-- init users 

INSERT INTO TEST.ACCOUNT (ACTIVE, CREATEDAT, EMAIL, ISADMIN, "NAME", PASSWORD, SALT) 
	VALUES (1, '2014-04-13 15:57:24.874', 'tomas', 1, 'tomas', 'c0cecb2275546c2c500cbe83be48b91b4a453a855e66adbf6711b5fd8345c051', '4991934116191320543');
INSERT INTO TEST.ACCOUNT (ACTIVE, CREATEDAT, EMAIL, ISADMIN, "NAME", PASSWORD, SALT) 
	VALUES (1, '2014-04-13 15:57:34.197', 'asdf', 0, 'asdf', '19f5307a2aa5e93c6f4233d94e45e2a9898a458f5e8d1917959d75a5f5d985bc', '12408627716164010523');
INSERT INTO TEST.ACCOUNT (ACTIVE, CREATEDAT, EMAIL, ISADMIN, "NAME", PASSWORD, SALT) 
	VALUES (0, '2014-04-13 15:57:41.878', 'aaaa', 0, 'aaaa', '5558a6228ae8da946ed1bec443c21bd49e8ab6e6e837f03f82893568d34a8c8c', '18175087180324813479');

-- init items 

INSERT INTO TEST.ITEM ("NAME", PRICE) 
	VALUES ('item nr. 1', 15.00);
INSERT INTO TEST.ITEM ("NAME", PRICE) 
	VALUES ('item nr. 2', 30.00);
INSERT INTO TEST.ITEM ("NAME", PRICE) 
	VALUES ('item nr. 5', 89.00);
INSERT INTO TEST.ITEM ("NAME", PRICE) 
	VALUES ('item nr. 10', 12.00);
INSERT INTO TEST.ITEM ("NAME", PRICE) 
	VALUES ('item nr. 15', 1.00);


-- 
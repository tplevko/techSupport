-- initialize DB snippet

-- init users 

INSERT INTO TEST.ACCOUNT (ACTIVE, CREATEDAT, EMAIL, "NAME", PASSWORD, SALT) 
	VALUES (0, '2014-04-05 19:47:12.155', 'asd', 'asd', 'c9415de10feff37817eb8398a3386c97460001fa10fbcdf65fec9db497ed3a78', '9847200715462190123');

INSERT INTO TEST.ACCOUNT (ACTIVE, CREATEDAT, EMAIL, "NAME", PASSWORD, SALT) 
	VALUES (1, '2014-04-05 19:55:16.658', 'tomasplevko@seznam.cz', 'tomas', '9ef92c751267bcb4622536bdd33cb3d35f3534c8df6c426e08dcc3c00d4a0bd9', '7449030952639863981');

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
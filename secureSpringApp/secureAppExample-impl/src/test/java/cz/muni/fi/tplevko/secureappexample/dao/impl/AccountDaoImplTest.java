package cz.muni.fi.tplevko.secureappexample.dao.impl;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tplevko
 */
public class AccountDaoImplTest {

    
    
    
    /////////////////////////
    // TODO : spravit nejake testy... 
    /////////////////////////
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private AccountDaoImpl accountDao;
    private EntityManager em;
    private InternetAddress inetAddress;

    private Account account;

    @Before
    public void setUp() throws AddressException {

        em = Persistence.createEntityManagerFactory("TestInMemoryPU").createEntityManager();
        accountDao = new AccountDaoImpl();
        accountDao.setEm(em);

        account = new Account();
        account.setEmail("tomasplevko@gmail.com");
        account.setName("tomas");
        account.setPassword("password");
        account.setSalt("salt");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createAccount method, of class AccountDaoImpl.
     */
//    @Test
    public void testCreateAccount() {
        System.out.println("createAccount");
        AccountDaoImpl instance = new AccountDaoImpl();

        Account expResult = account;

//        Account result = instance.createAccount(account);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAccount method, of class AccountDaoImpl.
     */
//    @Test
    public void testDeleteAccount() {
        System.out.println("deleteAccount");
        Account account = null;
        AccountDaoImpl instance = new AccountDaoImpl();
        instance.deleteAccount(account);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAccount method, of class AccountDaoImpl.
     */
//    @Test
    public void testUpdateAccount() {
        System.out.println("updateAccount");
        Account account = null;
        AccountDaoImpl instance = new AccountDaoImpl();
        Account expResult = null;
//        Account result = instance.updateAccount(account);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountAccrodToId method, of class AccountDaoImpl.
     */
//    @Test
    public void testFindAccountAccrodToId() {
        System.out.println("findAccountAccrodToId");
        Long id = null;
        AccountDaoImpl instance = new AccountDaoImpl();
        Account expResult = null;
        Account result = instance.findAccountById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountAccordToName method, of class AccountDaoImpl.
     */
//    @Test
    public void testFindAccountAccordToName() {
        System.out.println("findAccountAccordToName");
        String name = "";
        AccountDaoImpl instance = new AccountDaoImpl();
        Account expResult = null;
        Account result = instance.findAccountByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountAccordToEmail method, of class AccountDaoImpl.
     */
//    @Test
    public void testFindAccountAccordToEmail() {
        System.out.println("findAccountAccordToEmail");
        InternetAddress email = null;
        AccountDaoImpl instance = new AccountDaoImpl();
        Account expResult = null;
//        Account result = instance.findAccountByEmail(email);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

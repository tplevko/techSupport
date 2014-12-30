package cz.muni.fi.tplevko.techsupport.daos;

import cz.muni.fi.tplevko.techsupport.dao.CustomerDao;
import cz.muni.fi.tplevko.techsupport.dao.PasswordChangeDao;
import cz.muni.fi.tplevko.techsupport.entity.Customer;
import cz.muni.fi.tplevko.techsupport.entity.PasswordChange;
import java.util.Date;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author tplevko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
@Transactional
public class PasswordChangeDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Logger log = Logger.getLogger(PasswordChangeDaoTest.class.getName());

    @Autowired
    private PasswordChangeDao passwordChangeDao;

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testAddNullEmployee() {

        try {
            passwordChangeDao.createPasswordChange(null);
            fail("added passwordChange with null as parameter");
        } catch (DataAccessException iae) {
            //OK
        }
    }

    @Test
    public void testAddInvalidPasswordChange() {

        PasswordChange passwordChange = new PasswordChange();

        passwordChange.setRequester(null);

        assertNull("the passwordChange should not exist yet :", passwordChange.getId());

        try {
            passwordChangeDao.createPasswordChange(passwordChange);
            fail("added passwordChange with null account parameter");

        } catch (DataAccessException e) {
            //OK
        }
    }

    @Test
    public void testAddPasswordChange() {

        PasswordChange passwordChange = new PasswordChange();

        Customer customer = createCustomer("jano", "hlavaty", "jano@hlavaty.com",
                "aaa", "aaa", true);

        assertNull("the cutomer should not exist yet :", customer.getId());

        try {
            customerDao.createCustomer(customer);

            passwordChange.setRequester(customer);

            passwordChangeDao.createPasswordChange(passwordChange);

        } catch (IllegalArgumentException e) {
            fail();
        }

        log.info("**************** " + passwordChange.getId());

        assertNotNull("the customer should be created by now : ", customer.getId());
        assertNotNull("the passwordChange should be created by now : ", passwordChange.getId());
    }

    @Test
    public void testAddPasswordChangeTest() {

        PasswordChange passwordChange = new PasswordChange();

        Customer customer = createCustomer("jano", "hlavaty", "jano@hlavaty.com",
                "aaa", "aaa", true);

        assertNull("the cutomer should not exist yet :", customer.getId());

        try {
            customerDao.createCustomer(customer);

            passwordChange.setRequester(customer);

            passwordChangeDao.createPasswordChange(passwordChange);

        } catch (IllegalArgumentException e) {
            fail();
        }

        log.info("**************** " + passwordChange.getId());

        assertNotNull("the customer should be created by now : ", customer.getId());
        assertNotNull("the passwordChange should be created by now : ", passwordChange.getId());
    }

    public void deletePasswordChangeTest() {

        try {
            passwordChangeDao.deletePasswordChange(null);
            fail("We need to pass employee to the delete method");
        } catch (DataAccessException ex) {
            // OK
        }
        Customer customer = createCustomer("jano", "hlavaty", "jano@hlavaty.com",
                "aaa", "aaa", true);
        customerDao.createCustomer(customer);

        PasswordChange passChange = createPassChange(customer, false, null);

        passwordChangeDao.createPasswordChange(passChange);

        String id = passChange.getId();

        PasswordChange change = passwordChangeDao.findPasswordChangeId(id);
        assertNotNull("Passchange should be persisted already :", change);
        passwordChangeDao.deletePasswordChange(passChange);

        assertNull("Pass change should not exist anymore :", passwordChangeDao.findPasswordChangeId(id));

    }

    @Test
    public void retrieveEmployeeByIdTest() {

        try {
            passwordChangeDao.findPasswordChangeId(null);
            fail("passchange has to be specified");
        } catch (DataAccessException ex) {
            // OK
        }

        Customer customer = createCustomer("jano", "hlavaty", "jano2@hlavaty.com",
                "aaa", "aaa", true);
        customerDao.createCustomer(customer);
        PasswordChange passChange = createPassChange(customer, false, null);

        passwordChangeDao.createPasswordChange(passChange);

        PasswordChange foundPasswordChange = passwordChangeDao.findPasswordChangeId(passChange.getId());

        assertEquals(foundPasswordChange.getCreatedAt(), passChange.getCreatedAt());
    }

    private Customer createCustomer(String firstName, String lastName, String email,
            String password, String salt, boolean isActive) {

        Customer customer = new Customer();

        customer.setActive(isActive);
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPassword(password);
        customer.setSalt(salt);

        return customer;
    }

    private PasswordChange createPassChange(Customer requester, boolean executed, Date finishDate) {

        PasswordChange passChange = new PasswordChange();

        passChange.setRequester(requester);
        passChange.setExecuted(executed);
        passChange.setFinished(finishDate);

        return passChange;
    }

}

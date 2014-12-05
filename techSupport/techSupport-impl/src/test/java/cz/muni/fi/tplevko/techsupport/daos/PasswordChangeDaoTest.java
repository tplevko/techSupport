package cz.muni.fi.tplevko.techsupport.daos;

import cz.muni.fi.tplevko.techsupport.dao.CustomerDao;
import cz.muni.fi.tplevko.techsupport.dao.EmployeeDao;
import cz.muni.fi.tplevko.techsupport.dao.PasswordChangeDao;
import cz.muni.fi.tplevko.techsupport.entity.Customer;
import cz.muni.fi.tplevko.techsupport.entity.Employee;
import cz.muni.fi.tplevko.techsupport.entity.PasswordChange;
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
    private EmployeeDao employeeDao;

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

//    @Test
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

//    @Test
    public void testAddPasswordChangeForEmployee() {

        PasswordChange passwordChange = new PasswordChange();

        Employee employee = createEmployee("jano", "hlavaty", "jano@hlavaty.com",
                "aaa", "aaa", true, true);

        assertNull("the employee should not exist yet :", employee.getId());

        try {
            employeeDao.createEmployee(employee);

            passwordChange.setRequester(employee);

            passwordChangeDao.createPasswordChange(passwordChange);

        } catch (IllegalArgumentException e) {
            fail();
        }

        log.info("**************** " + passwordChange.getId());

        assertNotNull("the employee should be created by now : ", employee.getId());
        assertNotNull("the passwordChange should be created by now : ", passwordChange.getId());
    }

//    @Test
    public void testAddPasswordChangeForCustomer() {

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

    private Employee createEmployee(String firstName, String lastName, String email,
            String password, String salt, boolean isAdmin, boolean isActive) {

        Employee employee = new Employee();

        employee.setActive(isActive);
        employee.setEmail(email);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setIsAdmin(isAdmin);
        employee.setPassword(password);
        employee.setSalt(salt);

        return employee;
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
}

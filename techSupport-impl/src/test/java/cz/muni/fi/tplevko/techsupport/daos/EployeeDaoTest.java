package cz.muni.fi.tplevko.techsupport.daos;

import cz.muni.fi.tplevko.techsupport.dao.EmployeeDao;
import cz.muni.fi.tplevko.techsupport.entity.Employee;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.Before;
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
public class EployeeDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private EmployeeDao employeeDao;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests for reateEmployee method with different inputs
     */
    @Test
    public void testAddNullEmployee() {

        try {
            employeeDao.createEmployee(null);
            fail("added employee with null as parameter");
        } catch (DataAccessException iae) {
            //OK
        }
    }

    @Test
    public void testAddInvalidEmployee() {

        Employee employee = new Employee();

        employee.setActive(true);
        employee.setEmail("employee@employee.cz");
        employee.setFirstName(null);
        employee.setLastName("hlavaty");
        employee.setIsAdmin(true);
        employee.setPassword("aaa");
        employee.setSalt("bbb");

        assertNull("the employee should not exist yet :", employeeDao.findEmployeeByEmail("employee@employee.cz"));

        try {
            employeeDao.createEmployee(employee);
            fail("added employee with null parameter");

        } catch (DataAccessException e) {
            //OK
        }
    }

    @Test
    public void testAddEmployee() {

        Employee employee = createEmployee("jano", "hlavaty", "jano@hlavaty.com",
                "aaa", "aaa", true, true);

        assertNull("the employee should not exist yet :", employee.getId());

        try {
            employeeDao.createEmployee(employee);

        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull("the employee should be created by now : ", employee.getId());
    }

    @Test
    public void retrieveEmployeeTest() {

        try {
            employeeDao.findEmployeeById(null);
            fail("We cant search without id given");
        } catch (DataAccessException ex) {
            // OK
        }

        Employee employee2 = createEmployee("jano2", "hlavaty2", "jano2@hlavaty2.com",
                "aaa2", "aaa2", true, true);
        try {
            employeeDao.findEmployeeById(employee2.getId());
            fail("Id should not be null");
        } catch (DataAccessException ex) {
            // OK
        }

        Employee employee3 = createEmployee("jano3", "hlavaty3", "jano3@hlavaty3.com",
                "aaa3", "aaa3", true, true);
        employeeDao.createEmployee(employee3);
        Employee retrievedEmployee = employeeDao.findEmployeeById(employee3.getId());
        assertNotNull("We should retrieve commited employee", retrievedEmployee);
        assertEquals(employee3.getId(), retrievedEmployee.getId());
        assertEquals(employee3.getEmail(), retrievedEmployee.getEmail());

        Employee retrievedEmployeeNull = employeeDao.findEmployeeById(100000L);
        assertNull("No employee should be retrieved", retrievedEmployeeNull);
    }

    @Test
    public void updateEmployeeTest() {
        try {
            employeeDao.updateEmployee(null);
            fail("We can't update employee with null parameter");
        } catch (DataAccessException ex) {
            // OK
        }

        Employee emptyEmployee = new Employee();
        emptyEmployee.setId(1L);

        try {
            employeeDao.updateEmployee(emptyEmployee);
            fail("Employee is empty");
        } catch (DataAccessException ex) {
            // OK
        }

        Employee noIdEmployee = createEmployee("jano4", "hlavaty4", "jano4@hlavaty4.com",
                "aaa4", "aaa4", true, true);
        try {
            employeeDao.updateEmployee(noIdEmployee);
            fail("Employee's id is null");
        } catch (DataAccessException ex) {
            // OK
        }

        Employee notInDbEmployee = createEmployee("jano5", "hlavaty5", "jano5@hlavaty5.com",
                "aaa5", "aaa5", true, true);
        notInDbEmployee.setId(999L);

        try {
            employeeDao.updateEmployee(notInDbEmployee);
            fail("Employee does not exist... we can't update such an employee");
        } catch (DataAccessException ex) {
            // OK
        }

        Employee employeeOk = createEmployee("jano6", "hlavaty6", "jano6@hlavaty.com",
                "aaa6", "aaa6", true, true);

        // create employee in DB :
        employeeDao.createEmployee(employeeOk);
        // update record :
        employeeOk.setLastName("nehlavaty");

        // save update :
        employeeDao.updateEmployee(employeeOk);

        Employee employeeRetrieved = employeeDao.findEmployeeByEmail(employeeOk.getEmail());
        assertEquals(employeeRetrieved.getId(), employeeOk.getId());
        assertEquals(employeeRetrieved.getLastName(), "nehlavaty");
    }

    @Test
    public void deleteEmployeeTest() {

        try {
            employeeDao.deleteEmployee(null);
            fail("We need to pass employee to the delete method");
        } catch (DataAccessException ex) {
            // OK
        }

        Employee noIdEmployee = createEmployee("jano7", "hlavaty7", "jano7@hlavaty.com",
                "aaa7", "aaa7", true, true);
        try {
            employeeDao.deleteEmployee(noIdEmployee);
            fail("We need employee with Id to delete it");
        } catch (DataAccessException ex) {
            // OK
        }

        Employee employeeOk = createEmployee("jano9", "hlavaty9", "jano9@hlavaty.com",
                "aaa", "aaa", true, true);
        employeeDao.createEmployee(employeeOk);

        Long id = employeeOk.getId();

        Employee emp = employeeDao.findEmployeeById(id);
        assertNotNull("Employee should be persisted already :", emp);
        employeeDao.deleteEmployee(employeeOk);

        assertNull("Employee should not exist anymore :", employeeDao.findEmployeeById(id));
    }

    @Test
    public void retrieveEmployeeByIdTest() {

        try {
            employeeDao.findEmployeeById(null);
            fail("employee has to be specified");
        } catch (DataAccessException ex) {
            // OK
        }

        Employee employee = createEmployee("janoA", "hlavatyA", "janoA@hlavaty.com",
                "aaaA", "aaaA", true, true);
        employeeDao.createEmployee(employee);

        Employee foundEmployee = employeeDao.findEmployeeById(employee.getId());

        assertEquals(foundEmployee.getEmail(), employee.getEmail());
    }

    @Test
    public void retrieveEmployeeByEmailTest() {

        try {
            employeeDao.findEmployeeByEmail(null);
            fail("employee email has to be specified");
        } catch (DataAccessException ex) {
            // OK
        }

        try {
            employeeDao.findEmployeeByEmail("");
            fail("employee email has to be specified");
        } catch (DataAccessException ex) {
            // OK
        }

        Employee employee = createEmployee("janoB", "hlavatyB", "janoB@hlavaty.com",
                "aaaB", "aaaB", true, true);

        employeeDao.createEmployee(employee);

        Employee foundEmployee = employeeDao.findEmployeeByEmail(employee.getEmail());

        assertEquals(foundEmployee.getId(), employee.getId());
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
}

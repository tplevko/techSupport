package cz.muni.fi.tplevko.techsupport.daos;

import cz.muni.fi.tplevko.techsupport.dao.EmployeeDao;
import cz.muni.fi.tplevko.techsupport.dao.impl.EmployeeDaoImpl;
import cz.muni.fi.tplevko.techsupport.entity.Employee;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author tplevko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
@Transactional
public class EployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addGoal method, of class GoalDAOImpl.
     */
    @Test
    public void testAddNullEmployee() {

        try {
            employeeDao.createEmployee(null);
            fail("added employee will fail...");
        } catch (IllegalArgumentException iae) {
            //OK
        }
    }

    @Test
    public void testAddEmployee() {

        Employee employee = new Employee();

        employee.setActive(true);
        employee.setEmail("employee@employee.cz");
        employee.setFirstName("jano");
        employee.setLastName("hlavaty");
        employee.setIsAdmin(true);
        employee.setPassword("aaa");
        employee.setSalt("bbb");

        assertNull("the employee should not exist yet :", employeeDao.findEmployeeByEmail(employee.getEmail()));

        try {
            employeeDao.createEmployee(employee);

        } catch (IllegalArgumentException e) {
            fail();
        }

        Employee result = employeeDao.findEmployeeByEmail("employee@employee.cz");
        assertNotNull("the employee should be created by now : ", result);
    }
}

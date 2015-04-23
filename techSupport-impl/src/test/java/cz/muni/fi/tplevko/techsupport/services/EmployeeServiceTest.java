//package cz.muni.fi.tplevko.techsupport.services;
//
//import cz.muni.fi.tplevko.techsupport.dao.EmployeeDao;
//import cz.muni.fi.tplevko.techsupport.entity.Employee;
//import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
//import java.util.Arrays;
//import java.util.List;
//import java.util.logging.Logger;
//import org.dozer.DozerBeanMapper;
//import org.junit.After;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import static org.mockito.Mockito.when;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.util.ReflectionTestUtils;
//
///**
// *
// * @author tplevko
// */
//@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
//public class EmployeeServiceTest {
//
//    protected static final Logger log = Logger.getLogger(EmployeeServiceTest.class.getName());
//
//    @Mock
//    private EmployeeDao employeeDao;
//
//    private DozerBeanMapper mapper;
//
//    private EmployeeService employeeService;
//
//    @Before
//    public void setUp() {
//        mapper = new DozerBeanMapper();
//        employeeService = new EmployeeServiceImpl();
//        ReflectionTestUtils.setField(employeeService, "employeeDao", employeeDao);
//        ReflectionTestUtils.setField(employeeService, "mapper", mapper);
//    }
//
//    @After
//    public void tearDown() {
//        employeeService = null;
//        mapper = null;
//    }
//
//    @Test
//    public void createEmployeeTest() {
//
//        log.info("initializing the test method");
//
//        Employee employee = createEmployee("jano", "hlavaty", "jano@hlavaty.com",
//                "aaa", "aaa", true, true);
//
//        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
//
//        employeeService.createEmployee(employeeDto);
//
//        ArgumentCaptor<Employee> argument = ArgumentCaptor.forClass(Employee.class);
//        Mockito.verify(employeeDao).createEmployee(argument.capture());
//        deepEqualsEmployee(employee, argument);
//    }
//
//    private void deepEqualsEmployee(Employee employee, ArgumentCaptor<Employee> argument) {
//
//        assertTrue("IDs are not same", employee.getId() == argument.getValue().getId());
//        assertEquals("first names are different", employee.getFirstName(), argument.getValue().getFirstName());
//        assertEquals("last names are different", employee.getLastName(), argument.getValue().getLastName());
//
//    }
//
//    private Employee createEmployee(String firstName, String lastName, String email,
//            String password, String salt, boolean isAdmin, boolean isActive) {
//
//        Employee employee = new Employee();
//
//        employee.setActive(isActive);
//        employee.setEmail(email);
//        employee.setFirstName(firstName);
//        employee.setLastName(lastName);
//        employee.setIsAdmin(isAdmin);
//        employee.setPassword(password);
//        employee.setSalt(salt);
//
//        return employee;
//    }
//
//    @Test
//    public void testFindByEmail() {
//        Employee employee1 = createEmployee("jano1", "hlavaty1", "jano@hlavaty.com1",
//                "1", "1", true, true);
//        Employee employee2 = createEmployee("jano2", "hlavaty2", "jano@hlavaty.com2",
//                "2", "2", true, true);
//        employee1.setId(2L);
//        employee2.setId(3L);
//
//        List<Employee> employees = Arrays.asList(new Employee[]{employee1, employee2});
//        List<EmployeeDto> employeeDtoList = Arrays.asList(new EmployeeDto[]{
//            mapper.map(employee1, EmployeeDto.class),
//            mapper.map(employee2, EmployeeDto.class)
//        });
//
//        when(employeeDao.findEmployeeByEmail("jano@hlavaty.com1")).thenReturn(employee1);
//        EmployeeDto emplResult = employeeService.findEmployeeByEmail("jano@hlavaty.com1");
//
//        assertTrue(emplResult.getId() == 2L);
//    }
//
////    private EmployeeDto createEmployeeDto(String firstName, String lastName, String email,
////            String password, String salt, boolean isAdmin, boolean isActive) {
////
////        EmployeeDto employee = new EmployeeDto();
////
////        employee.setActive(isActive);
////        employee.setEmail(email);
////        employee.setFirstName(firstName);
////        employee.setLastName(lastName);
////        employee.setIsAdmin(isAdmin);
////        employee.setPassword(password);
////        employee.setSalt(salt);
////
////        return employee;
////    }
//}

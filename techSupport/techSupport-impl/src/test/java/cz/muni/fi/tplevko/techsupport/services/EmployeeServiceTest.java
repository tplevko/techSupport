//package cz.muni.fi.tplevko.techsupport.services;
//
//import cz.muni.fi.tplevko.techsupport.dao.impl.EmployeeDaoImpl;
//import cz.muni.fi.tplevko.techsupport.entity.Employee;
//import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
//import java.util.logging.Logger;
//import org.dozer.DozerBeanMapper;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
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
//    @Autowired
//    @InjectMocks
//    private EmployeeService employeeService = new EmployeeServiceImpl();
//
//    @Mock
//    EmployeeDaoImpl employeeDaoImpl;
//
//    @Mock
//    DozerBeanMapper mapper;
//
//    @Test
//    public void createEmployeeTest() {
//
//        log.info("initializing the test method");
//
//        Employee employee = createEmployee("jano", "hlavaty", "jano@hlavaty.com",
//                "aaa", "aaa", true, true);
//        EmployeeDto employeeDto = createEmployeeDto("jano", "hlavaty", "jano@hlavaty.com",
//                "aaa", "aaa", true, true);
//
//        employeeService.createEmployee(employeeDto);
//
//        ArgumentCaptor<Employee> argument = ArgumentCaptor.forClass(Employee.class);
//        Mockito.verify(employeeDaoImpl).createEmployee(argument.capture());
//        deepEqualsEmployee(employee, argument);
//    }
//
//    private void deepEqualsEmployee(Employee employee, ArgumentCaptor<Employee> argument) {
//
//        assertTrue("IDs are not same", employee.getId() == argument.getValue().getId());
//        assertEquals("first names are different", employee.getFirstName(), argument.getValue().getFirstName());
//        assertEquals("last names are different", employee.getLastName(), argument.getValue().getLastName());
//
//        // TODO : add
//    }
//
////
////    @Test
////    public void createForestWithWrongAttributesTest() {
////        try {
////            forestService.addForest(null);
////            fail("createForest with null DTO didn't throw IAE");
////        } catch (IllegalArgumentException e) {
////            //ok
////        }
////
////        String loc = "location1";
////        ForestDTO forestDto = createForestDTO(null, loc);
////
////        try {
////            forestService.addForest(forestDto);
////            fail("created forest with null name");
////        } catch (IllegalArgumentException e) {
////            //ok
////        }
////    }
////
////    @Test
////    public void retrieveForestTest() {
////        String name = "name1";
////        String loc = "location1";
////        Forest f = createForest(name, loc);
////        f.setId(1L);
////
////        ForestDTO forestDto = createForestDTO(name, loc);
////        forestDto.setId(1L);
////
////        Mockito.stub(forestDAOImpl.retrieveForest(1L)).toReturn(f);
////        ForestDTO fDTO = forestService.retrieveForest(1l);
////        deepEqualsDTO(fDTO, forestDto);
////    }
////
////    @Test
////    public void getForestWithWrongAttributesTest() {
////        try {
////            forestService.retrieveForest(null);
////            fail("getForest with null id didn't throw IAE");
////        } catch (IllegalArgumentException e) {
////            //ok
////        }
////    }
////
////    @Test
////    public void updateForest() {
////        String name = "forest1";
////        String loc = "location1";
////        ForestDTO forestDto = createForestDTO(name, loc);
////        forestDto.setId(1L);
////
////        forestService.updateForest(forestDto);
////
////        ArgumentCaptor<Forest> argument = ArgumentCaptor.forClass(Forest.class);
////        Mockito.verify(forestDAOImpl).updateForest(argument.capture());
////
////        Forest forest = createForest(name, loc);
////        forest.setId(1L);
////
////        deepEqualsForest(forest, argument);
////    }
////
////    @Test
////    public void updateForestWithWrongAttributeTest() {
////        try {
////            forestService.updateForest(null);
////            fail("updateForest with null DTO didn't throw IAE");
////        } catch (IllegalArgumentException e) {
////            //ok
////        }
////
////        String loc = "location1";
////        ForestDTO forestDto = createForestDTO(null, loc);
////        forestDto.setId(1L);
////
////        try {
////            forestService.updateForest(forestDto);
////            fail("updated forest with null name");
////        } catch (IllegalArgumentException e) {
////            //ok
////        }
////
////        forestDto.setId(null);
////        forestDto.setName("forest 1");
////
////        try {
////            forestService.updateForest(forestDto);
////            fail("updated forest with null id");
////        } catch (IllegalArgumentException e) {
////            //ok
////        }
////    }
////
////    @Test
////    public void removeForestTest() {
////        String name = "forest1";
////        String loc = "location1";
////        ForestDTO forestDto = createForestDTO(name, loc);
////        forestDto.setId(1L);
////
////        forestService.deleteForest(forestDto);
////
////        ArgumentCaptor<Forest> argument = ArgumentCaptor.forClass(Forest.class);
////
////        Mockito.verify(forestDAOImpl).deleteForest(argument.capture());
////
////        Forest forest = createForest(name, loc);
////        forest.setId(1L);
////
////        deepEqualsForest(forest, argument);
////    }
////
////    @Test
////    public void removeForestWithWronAttributesTest() {
////        try {
////            forestService.deleteForest(null);
////            fail("removeForest with null DTO didn't throw IAE");
////        } catch (IllegalArgumentException e) {
////            //ok
////        }
////
////        String loc = "location1";
////        String name = "forest 1";
////        ForestDTO forestDto = createForestDTO(name, loc);
////
////        try {
////            forestService.deleteForest(forestDto);
////            fail("removed forest with null id");
////        } catch (IllegalArgumentException e) {
////            //ok
////        }
////    }
////
////    @Test
////    public void retrieveMushroomsInForestTest() {
////        MushroomDTO m1 = new MushroomDTO();
////        m1.setId(1L);
////        m1.setName("mushroom 1");
////        m1.setIntervalTime(new Interval(DateTimeConstants.MARCH, DateTimeConstants.JULY));
////        m1.setType(MushroomType.EDIBLE);
////
////        MushroomDTO m2 = new MushroomDTO();
////        m2.setId(2L);
////        m2.setName("mushroom 2");
////        m2.setIntervalTime(new Interval(DateTimeConstants.APRIL, DateTimeConstants.JUNE));
////        m2.setType(MushroomType.POISONOUS);
////
////        String name = "forest1";
////        String loc = "location1";
////        ForestDTO forestDto = createForestDTO(name, loc);
////        forestDto.setId(1L);
////
////        Forest f = createForest(name, loc);
////        f.setId(1L);
////
////        List<MushroomDTO> mushrooms = new ArrayList<>();
////        mushrooms.add(m1);
////        mushrooms.add(m2);
////
////        List<Mushroom> mushroomsEntities = MushroomConverter.mushroomEntityFromMushroomDTO(mushrooms);
////
////        forestDto.setMushroomsInForest(mushrooms);
////        f.setMushroomsInForest(mushroomsEntities);
////
////        Mockito.stub(forestDAOImpl.retrieveMushroomsInForest(f)).toReturn(mushroomsEntities);
////        List<MushroomDTO> mdtos = forestService.retrieveMushroomsInForest(forestDto);
////
////        assertTrue(mushrooms.size() == mdtos.size());
////        for (int i = 0; i < mushrooms.size(); i++) {
////            assertEquals(mushrooms.get(i), mdtos.get(i));
////            deepEquealsMushroomDTO(mushrooms.get(i), mdtos.get(i));
////        }
////    }
////
////    @Test
////    public void retrieveMushroomsWithWrongAttributesTest() {
////        try {
////            forestService.retrieveMushroomsInForest(null);
////            fail("retrieveMushroomsWithWrongAttributes with null argument did not throw IAE");
////        } catch (IllegalArgumentException e) {
////            //ok
////        }
////
////        String loc = "location1";
////        String name = "forest 1";
////        ForestDTO forestDto = createForestDTO(name, loc);
////
////        try {
////            forestService.retrieveMushroomsInForest(forestDto);
////            fail("retrieveMushroomWithWrongAttributes forestDTO with null id");
////        } catch (IllegalArgumentException e) {
////            //ok
////        }
////    }
////
////    private ForestDTO createForestDTO(String name, String loc) {
////        ForestDTO forestDto = new ForestDTO();
////        forestDto.setName(name);
////        forestDto.setLocationInfo(loc);
////
////        return forestDto;
////    }
////
////    private Forest createForest(String name, String loc) {
////        Forest f = new Forest();
////        f.setName(name);
////        f.setLocationInfo(loc);
////
////        return f;
////    }
////
////    private void deepEquealsMushroomDTO(MushroomDTO mushroomDTO, MushroomDTO argument) {
////        assertTrue("IDs are not same", mushroomDTO.getId() == argument.getId());
////        assertEquals("Names of mushrooms are different", mushroomDTO.getName(), argument.getName());
////        assertEquals("Interval are different", mushroomDTO.getIntervalTime(), argument.getIntervalTime());
////        assertEquals("Type is different", mushroomDTO.getType(), MushroomDTO.MushroomType.valueOf(argument.getType().toString()));
////    }
////
////
////    private void deepEqualsDTO(ForestDTO forestDto, ForestDTO argument) {
////        assertTrue("IDs are not same", forestDto.getId() == argument.getId());
////        assertEquals("Names of forest are different", forestDto.getName(), argument.getName());
////        assertEquals("Locations of forest are different", forestDto.getLocationInfo(), argument.getLocationInfo());
////    }
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
//    private EmployeeDto createEmployeeDto(String firstName, String lastName, String email,
//            String password, String salt, boolean isAdmin, boolean isActive) {
//
//        EmployeeDto employee = new EmployeeDto();
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
//}

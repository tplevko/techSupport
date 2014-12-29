package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.dao.EmployeeDao;
import cz.muni.fi.tplevko.techsupport.entity.Employee;
import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tplevko
 */
@Service(value = "employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    // Use logger
    private static final Logger log = Logger.getLogger(EmployeeServiceImpl.class.getName());

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public void createEmployee(EmployeeDto employeeDto) {

        if (employeeDto == null) {
            throw new IllegalArgumentException("Employee to be created is null");
        }

        if (employeeDto.getId() != null) {
            throw new IllegalArgumentException("Employee has to have id set");
        }

        Employee employee = mapper.map(employeeDto, Employee.class);
        log.info("employee created : empoyee email : " + employee.getEmail());
        employeeDao.createEmployee(employee);

    }

    @Override
    @Transactional
    public void updateEmployee(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            throw new IllegalArgumentException("Employee to be created is null");
        }

        if (employeeDto.getId() == null) {
            throw new IllegalArgumentException("Employee has not set id");
        }

        Employee employee = mapper.map(employeeDto, Employee.class);
        log.info("employee updated : empoyee email : " + employee.getEmail());
        employeeDao.updateEmployee(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(EmployeeDto employeeDto) {

        if (employeeDto == null) {
            throw new IllegalArgumentException("Employee to be created is null");
        }

        if (employeeDto.getId() == null) {
            throw new IllegalArgumentException("Employee has not set id");
        }
        log.info("employee deleted : empoyee email : " + employeeDto.getEmail());
        Employee employee = mapper.map(employeeDto, Employee.class);
        employeeDao.deleteEmployee(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto findEmployeeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        Employee employee = employeeDao.findEmployeeById(id);

        return mapper.map(employee, EmployeeDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto findEmployeeByEmail(String email) {

        if (email == null) {
            throw new IllegalArgumentException("email can't be nulll");
        }

        EmployeeDto employeeDto = null;
        Employee employee = employeeDao.findEmployeeByEmail(email);

        if (employee != null) {
            employeeDto = mapper.map(employee, EmployeeDto.class);
        }

        return employeeDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees = employeeDao.getAllEmployees();

        List<EmployeeDto> employeesReturn = new ArrayList<>();
        for (Employee e : employees) {
            employeesReturn.add(mapper.map(e, EmployeeDto.class));
        }
        return employeesReturn;
    }
}

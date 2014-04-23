package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface EmployeeService {

    public void createEmployee(EmployeeDto employeeDto);

    public void updateEmployee(EmployeeDto employeeDto);

    public void deleteEmployee(EmployeeDto employeeDto);

    public EmployeeDto findEmployeeById(Long id);

    public EmployeeDto findEmployeeByEmail(String email);

    public List<EmployeeDto> getAllEmployees();

    public void activateEmployeeAccount(EmployeeDto employeeDto);
}

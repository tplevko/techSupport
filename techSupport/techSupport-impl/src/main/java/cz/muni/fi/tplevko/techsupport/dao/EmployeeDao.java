package cz.muni.fi.tplevko.techsupport.dao;

import cz.muni.fi.tplevko.techsupport.entity.Employee;
import java.util.List;

/**
 * Interface for the basic employee DAO
 * 
 * @author tplevko
 */
public interface EmployeeDao {

    public void createEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public void deleteEmployee(Employee employee);

    public Employee findEmployeeById(Long id);

    public Employee findEmployeeByEmail(String email);

    public List<Employee> getAllEmployees();
}

package cz.muni.fi.tplevko.techsupport.managedbeans.employee;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component
@ManagedBean
@Scope("session")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private EmployeeDto employeeDto;
    private List<EmployeeDto> employeeList;

    @PostConstruct
    public void init() {

        employeeDto = new EmployeeDto();
        employeeList = new ArrayList<EmployeeDto>();
    }

    public EmployeeDto getEmployeeDto(long employeeId) {
        
        employeeDto = employeeService.findEmployeeById(employeeId);
        
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public List<EmployeeDto> getEmployeeList() {
        employeeList = employeeService.getAllEmployees();
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeDto> employeeList) {
        this.employeeList = employeeList;
    }
    
    // TODO : ADMIN richts
    public void removeEmployee() {
        
    }
    
    public void deactivateEmployee() {
        
    }
    
    public void editEmployeeBefore() {
        
    }
    
//    
//    public String createEmployee() {
//        
//        // TODO : create automatic passwd generation... 
//        
//        employeeDto.setPassword("passwd");
//        
//        employeeService.createEmployee(employeeDto);
//        
//        return "";
//    }

}
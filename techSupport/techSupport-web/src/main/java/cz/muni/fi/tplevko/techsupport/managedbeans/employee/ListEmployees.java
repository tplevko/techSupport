package cz.muni.fi.tplevko.techsupport.managedbeans.employee;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "listEmployees")
@ManagedBean
@Scope("session")
public class ListEmployees {
    
    @Autowired
    private EmployeeService employeeService;
    
    private EmployeeDto employeeDto;

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

}

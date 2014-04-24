package cz.muni.fi.tplevko.techsupport.managedbeans.employee;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "createEmployee")
@ManagedBean
@Scope("session")
public class CreateEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    @PostConstruct
    public void init() {

        employeeDto = new EmployeeDto();
    }

    @Autowired
    private EmployeeService employeeService;

    private EmployeeDto employeeDto;

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    private String createEmployee() {

        employeeService.createEmployee(employeeDto);

        return "/employee/admin/employeeList?faces-redirect=true";
    }
}

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
@Component(value = "listEmployees")
@ManagedBean
@Scope("session")
public class ListEmployees {

    @Autowired
    private EmployeeService employeeService;

    private EmployeeDto employeeDto;
    private List<EmployeeDto> employeeeList;

    @PostConstruct
    public void init() {

        employeeDto = new EmployeeDto();
        employeeeList = new ArrayList<EmployeeDto>();
    }

    public List<EmployeeDto> getEmployeeeList() {
        employeeeList = employeeService.getAllEmployees();
        return employeeeList;
    }

    public void setEmployeeeList(List<EmployeeDto> employeeeList) {
        this.employeeeList = employeeeList;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }
}

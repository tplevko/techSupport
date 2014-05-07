package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import cz.muni.fi.tplevko.techsupport.utils.ShaEncoder;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "employeeOperations")
@ManagedBean
@Scope("session")
public class EmployeeOperations implements Serializable {

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


    public String createEmployee() {

        String salt;
        Sha256Hash passwordHash;

        // TODO : zmenit...    
        // TODO : treba spravit to generovanie hesiel pre zamestnancov
        employeeDto.setPassword("passwd");

        employeeDto.getPassword();

        salt = ShaEncoder.generateSalt();
        passwordHash = ShaEncoder.hash(employeeDto.getPassword(), salt);

        employeeDto.setSalt(salt);
        employeeDto.setPassword(passwordHash.toHex());

        employeeService.createEmployee(employeeDto);

        return "/employee/admin/employee/employeeList?faces-redirect=true";
    }

    public String editEmployeeBefore() {

        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long employeeId = Long.valueOf(parameterMap.get("employeeId"));
        employeeDto = employeeService.findEmployeeById(employeeId);

        return "/employee/admin/employee/editEmployee?faces-redirect=true";
    }

    public String addEmployeeBefore() {
        employeeDto = new EmployeeDto();
        return "/employee/admin/employee/addEmployee?faces-redirect=true";
    }

    public String editEmployee() {

        employeeService.updateEmployee(employeeDto);
        return "/employee/admin/employee/employeeList?faces-redirect=true";
    }

    public String deleteEmployee() {
        
        Long id = employeeDto.getId();
        EmployeeDto employee = employeeService.findEmployeeById(id);
        employeeService.deleteEmployee(employee);
        return "/employee/admin/employee/employeeList?faces-redirect=true";
    }
}

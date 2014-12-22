package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import cz.muni.fi.tplevko.techsupport.utils.GenerateEmployeePassword;
import cz.muni.fi.tplevko.techsupport.utils.ShaEncoder;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The class is responsible for employee related operations. Some functions are
 * accessible only to the admin.
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

    public void createEmployee() {

        String salt = ShaEncoder.generateSalt();
        String employeePassword = GenerateEmployeePassword.generatePasswd();

        Sha256Hash passwordHash = ShaEncoder.hash(employeePassword, salt);

        employeeDto.setSalt(salt);
        employeeDto.setPassword(passwordHash.toHex());

        employeeService.createEmployee(employeeDto);

        String message = "please, send this password to the new employee : " + employeePassword;

        // TODO : 
        FacesContext.getCurrentInstance().addMessage("emplPass", new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));

    }

    public String editEmployeeBefore() {

        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long employeeId = Long.valueOf(parameterMap.get("employeeId"));
        employeeDto = employeeService.findEmployeeById(employeeId);

        return "/employee/admin/employee/editEmployee?faces-redirect=true";
    }

    public String editPersonalInfoBefore() {

        String currentEmployee = SecurityUtils.getSubject().getPrincipal().toString();

        employeeDto = employeeService.findEmployeeByEmail(currentEmployee);

        return "/employee/technician/editPersonalInfo?faces-redirect=true";
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

    public String resetPassword() {

        // TODO : reset passwd method
        return "/employee/admin/employee/employeeList?faces-redirect=true";
    }
}

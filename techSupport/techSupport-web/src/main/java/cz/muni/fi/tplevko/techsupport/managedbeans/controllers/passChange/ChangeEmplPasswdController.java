package cz.muni.fi.tplevko.techsupport.managedbeans.controllers.passChange;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import cz.muni.fi.tplevko.techsupport.utils.ShaEncoder;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "changeEmplPasswdController")
@Scope(value = "session")
public class ChangeEmplPasswdController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String changeEmployeePassword() {

        String oldPasswordHash;
        String newPasswordHash;

        String currentEmployee = SecurityUtils.getSubject().getPrincipal().toString();
        EmployeeDto employee = employeeService.findEmployeeByEmail(currentEmployee);

        oldPasswordHash = ShaEncoder.hash(oldPassword, employee.getSalt()).toHex();
        newPasswordHash = ShaEncoder.hash(newPassword, employee.getSalt()).toHex();

        if (oldPasswordHash.equals(employee.getPassword())) {

            employee.setPassword(newPasswordHash);

            employeeService.updateEmployee(employee);

            return "Success";

        } else {
                        
            return "Failure";
        }
    }

    public String changeCustomerPassword() {

        String currentCustomer = SecurityUtils.getSubject().getPrincipal().toString();

        CustomerDto customer = customerService.findCustomerByEmail(currentCustomer);

        return null;
    }
    
    
}

package cz.muni.fi.tplevko.techsupport.managedbeans.init;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import cz.muni.fi.tplevko.techsupport.utils.ShaEncoder;
import java.util.List;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * This class is called once, after the system is started. The class creates admin
 * record in the database, if there is no admin record in the database.
 * 
 * The class uses properties file - admin.properties
 * 
 * @author tplevko
 */
public class StartupHandler { 
    
    @Autowired
    private EmployeeService employeeService;

    @Value("${admin.firstName}")
    private String adminFirstName;

    @Value("${admin.lastName}")
    private String adminLastName;

    @Value("${admin.password}")
    private String adminPasswd;

    @Value("${admin.email}")
    private String adminEmail;

    public void init() {

        EmployeeDto admin = new EmployeeDto();

        if (existsAdmin()) {
            return;

        } else {
            
            String salt;
            Sha256Hash passwordHash;

            salt = ShaEncoder.generateSalt();
            passwordHash = ShaEncoder.hash(adminPasswd, salt);

            admin.setActive(true);
            admin.setIsAdmin(true);
            admin.setEmail(adminEmail);
            admin.setLastName(adminLastName);
            admin.setFirstName(adminFirstName);
            
            admin.setPassword(passwordHash.toHex());
            admin.setSalt(salt);

            employeeService.createEmployee(admin);
        }
    }

    private boolean existsAdmin() {

        List<EmployeeDto> employeeList = employeeService.getAllEmployees();

        boolean adminExists = false;

        for (EmployeeDto e : employeeList) {
            if (e.isIsAdmin()) {
                adminExists = true;
            }
        }

        return adminExists;
    }
}

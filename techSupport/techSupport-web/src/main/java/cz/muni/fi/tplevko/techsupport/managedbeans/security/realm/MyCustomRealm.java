package cz.muni.fi.tplevko.techsupport.managedbeans.security.realm;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.utils.ShaEncoder;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import java.util.logging.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tom
 */
@Component("myCustomRealm")
public class MyCustomRealm extends AuthorizingRealm {

    private static final Logger LOG = Logger.getLogger(MyCustomRealm.class.getName());
    private static final String MESSAGE = "message";

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    public MyCustomRealm() {

        setName("myCustomRealm");
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME);
        matcher.setHashIterations(ShaEncoder.getIterations());
        setCredentialsMatcher(matcher);
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String email = (String) pc.getPrimaryPrincipal();

        CustomerDto customerAccount = customerService.findCustomerByEmail(email);
        EmployeeDto employeeAccount = employeeService.findEmployeeByEmail(email);

        if (customerAccount != null) {

            LOG.info("***** the employeeAccount value is: " + customerAccount.getEmail() + " *****");
            info.addRole("ROLE_USER");

        }

        if (employeeAccount != null) {

            LOG.info("***** the customerAccount value is: " + employeeAccount.getEmail() + " *****");
            
            if (employeeAccount.isIsAdmin()) {
                info.addRole("ROLE_ADMIN");
                info.addRole("ROLE_TECHNICIAN");
            } else {
                info.addRole("ROLE_TECHNICIAN");
            }
        }
//
//        info.addRole("ROLE_TECHNICIAN");
//        info.addRole("ROLE_ADMIN");
//        info.addRole("ROLE_USER");

        return info;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) at;

        String username = token.getUsername();
        String password = null;

        if (token.getPassword() != null) {
            password = new String(token.getPassword());
        }
        if (username == null || "".equals(username)) {
            return null;
        }
        if (password == null || "".equals(password)) {
            return null;
        }

        AccountDto customerAccount = null;
        AccountDto employeeAccount = null;
        AccountDto account = null;

        if (token.getUsername() != null && !"".equals(token.getUsername())) {

            customerAccount = customerService.findCustomerByEmail(token.getUsername());

            employeeAccount = employeeService.findEmployeeByEmail(token.getUsername());

            if (employeeAccount != null) {

                LOG.info("***** the employeeAccount value is: " + employeeAccount.getEmail() + " *****");
//
                account = employeeAccount;
            }

            if (customerAccount != null) {

                LOG.info("***** the customerAccount value is: " + customerAccount.getEmail() + " *****");

                account = customerAccount;
            }
        }

        try {
            // TODO : tu nebude zrejme len simple... 
            return new SimpleAuthenticationInfo(account.getEmail(), account.getPassword(), new SimpleByteSource(account.getSalt()), getName());
        } catch (Exception e) {
            return null;
        }

    }
}

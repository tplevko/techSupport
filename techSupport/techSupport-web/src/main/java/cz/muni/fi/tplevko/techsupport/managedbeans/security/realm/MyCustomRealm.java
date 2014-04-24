package cz.muni.fi.tplevko.techsupport.managedbeans.security.realm;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.utils.ShaEncoder;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
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

//    private Logger log = Logger.getLogger(this.getClass());  
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

    public void setUserService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String email = (String) pc.getPrimaryPrincipal();

        CustomerDto account = customerService.findCustomerByEmail(email);
        EmployeeDto employee = employeeService.findEmployeeByEmail(email);

        // TODO : toto sa mi zda na hovno...
        // Asi bude treba predsa len viacero tychto realmov...
        // co ak sa clovek s jednym mailom registruje na obe tie funkcie?

        
        
        if (account != null) {
            info.addRole("ROLE_USER");
            
        } else if (employee != null) {
            if (employee.isIsAdmin()) {
//                info.addRole("ROLE_USER");
                info.addRole("ROLE_ADMIN");
                info.addRole("ROLE_TECHNICIAN");
            } else {
//                info.addRole("ROLE_USER");
                info.addRole("ROLE_TECHNICIAN");
            }
        }

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
//            log.info("the attribute can't be null");
            return null;
        }
        if (password == null || "".equals(password)) {
//            log.info("the password can't be null");  
            return null;
        }

        CustomerDto account = null;

        if (token.getUsername() != null && !"".equals(token.getUsername())) {
            account = customerService.findCustomerByEmail(token.getUsername());
        }

        try {

            // TODO : tu nebude zrejme len simple... 
            return new SimpleAuthenticationInfo(account.getEmail(), account.getPassword(), new SimpleByteSource(account.getSalt()), getName());
//            return new SimpleAuthenticationInfo(account.getName(), password, getName());

        } catch (Exception e) {
//            log.info("用户名或密码错误");  
            setAttribute(MESSAGE, "用户名或密码错误");
            return null;
        }

    }

    private void setAttribute(String key, String value) {

        SecurityUtils.getSubject().getSession().setAttribute(key, value);
    }

}

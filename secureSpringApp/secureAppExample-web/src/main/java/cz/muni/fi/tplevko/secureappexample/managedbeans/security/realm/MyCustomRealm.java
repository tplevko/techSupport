/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.tplevko.secureappexample.managedbeans.security.realm;

import cz.muni.fi.tplevko.secureappexample.entity.dto.AccountDto;
import cz.muni.fi.tplevko.secureappexample.services.AccountService;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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
    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

        String username = (String) pc.getPrimaryPrincipal();
        AccountDto account = accountService.findAccountByName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if (account.isIsAdmin()) {

//            info.addStringPermission("access");
            info.addRole("ROLE_ADMIN");
            info.addRole("ROLE_USER");
            return info;
        } else {
//            info.addStringPermission("access");  
            info.addRole("ROLE_USER");
            return info;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {

        System.out.println("Pokusam sa autentizovat tomasa... ");

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

        ////////////////////
        AccountDto account = null;

        if (token.getUsername() != null && !"".equals(token.getUsername())) {
            account = accountService.findAccountByName(token.getUsername());
        }

        try {
            
            // TODO : tu nebude zrejme len simple... 
            
            return new SimpleAuthenticationInfo(account.getName(), password, getName());

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

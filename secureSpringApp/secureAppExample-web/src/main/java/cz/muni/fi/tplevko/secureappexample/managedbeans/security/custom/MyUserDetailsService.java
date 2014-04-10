package cz.muni.fi.tplevko.secureappexample.managedbeans.security.custom;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.services.AccountService;
import cz.muni.fi.tplevko.secureappexample.utils.ShaEncoder;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author tplevko
 */
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountService accountService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        ShaEncoder spe = new ShaEncoder();
        MyUserDetails d = new MyUserDetails();
        
        // TODO : toto by sa pouzilo v pripade, ze admin ma definovane heslo ako 
        // systemovu property
        
//        if (string.equals(adminUsername)) {
//            d.setIsAdmin(Boolean.TRUE);
//            d.setAuthorities(Arrays.asList(createRole("ROLE_ADMIN")));
//            d.setUsername(adminUsername);
//            d.setPassword(adminPassword);
//            
//            return d;
//        }
        
        Account account = accountService.findAccountByName(username);
        if (account == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        
        
        d.setIsAdmin(account.isIsAdmin());
        
        if (d.isIsAdmin()) {
            d.setAuthorities(Arrays.asList(createRole("ROLE_ADMIN")));
        } else {
            d.setAuthorities(Arrays.asList(createRole("ROLE_USER")));
        }
        d.setUsername(account.getName());
        d.setPassword(account.getPassword());

        return d;
    }
    
    private MyGrantedAuthority createRole(String role) {
        MyGrantedAuthority cga = new MyGrantedAuthority();
        cga.setAuthority(role);
        
        return cga;
    }

}

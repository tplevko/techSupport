package cz.muni.fi.tplevko.secureappexample.managedbeans.security.custom;

import cz.muni.fi.tplevko.secureappexample.entity.dto.AccountDto;
import cz.muni.fi.tplevko.secureappexample.services.AccountService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author tplevko
 */
public class LoginUserDetailsService implements UserDetailsService {
    
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {

        LoginUserDetails current = new LoginUserDetails();
        
        AccountDto account = accountService.findAccountByName(string);
        if (account == null) {
            throw new UsernameNotFoundException(string + " not found");
        }
        
        current.setIsAdmin(account.isIsAdmin());
        if (current.isIsAdmin()) {
            current.setAuthorities(Arrays.asList(createRole("ROLE_ADMIN")));
        } else {
            current.setAuthorities(Arrays.asList(createRole("ROLE_USER")));
        }
        current.setUsername(account.getName());
        current.setPassword(account.getPassword());

        return current;
    }
    
    private CustomGrantedAuthority createRole(String role) {
        CustomGrantedAuthority cga = new CustomGrantedAuthority();
        cga.setAuthority(role);
        
        return cga;
    }


}

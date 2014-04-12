package cz.muni.fi.tplevko.secureappexample.managedbeans.security.custom;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author tplevko
 */
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {

        return authority;
    }

}

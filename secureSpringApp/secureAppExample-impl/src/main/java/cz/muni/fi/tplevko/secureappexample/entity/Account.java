package cz.muni.fi.tplevko.secureappexample.entity;

import java.io.Serializable;
import java.util.Set;
import javax.mail.internet.InternetAddress;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.validation.constraints.NotNull;

/**
 *
 * @author tplevko
 */
@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 40, nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // TODO : tu bude presna dlza retazca...
    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 64, nullable = false)
    private String salt;

//    @Column(name = "created_at")
//    private Date createdAt;

    // TODO : ulozenie casu registracie
    // TODO : ako presne ukladat tie roly? 
//    @ElementCollection
//    private Set<String> roles;
//    @NotNull
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Set<String> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<String> roles) {
//        this.roles = roles;
//    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // TODO : zisti ako spravit dobry hashcode...
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    // TODO : prepisat equals 
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (object == null) {
            return false;
        }
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.tplevko.secureappexample.entity.Account[ id=" + id + " ]";
    }

}

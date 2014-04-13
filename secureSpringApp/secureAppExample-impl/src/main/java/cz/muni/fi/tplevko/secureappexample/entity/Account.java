package cz.muni.fi.tplevko.secureappexample.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tplevko
 */
@Entity
@NamedQueries({
    @NamedQuery(
        name = "Account.find",
        query = "SELECT a FROM Account a WHERE a.name = :name AND a.password = :password"),
    @NamedQuery(
        name = "Account.list",
        query = "SELECT a FROM Account a")
})

//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
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
    @Column(length = 256, nullable = false)
    private String password;

    @Column(length = 64, nullable = false)
    private String salt;

   
    // TODO : toto by mohla byt ta rola... by sa dal este enum s rolami a OK...
    
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "UserRoles", joinColumns = { @JoinColumn(name = "userId") })
//    @Column(name = "role")
//    private List<Role> roles;
    
    
    
    
    // TODO : ukladat adresu? 
    
//    @Column()
//    private String Address;

    // ROLES : ADMIN_ROLE, USER_ROLE, maybe use enum
    
//    @ElementCollection
//    private Set<String> roles;
    private boolean isAdmin;
    
    private boolean active;

    @Column(name = "createdAt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // auto generate timestamp on entity create time
    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

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
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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

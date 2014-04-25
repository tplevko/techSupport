package cz.muni.fi.tplevko.techsupport.entity;

/**
 *
 * @author tplevko
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "Employee.find",
            query = "SELECT a FROM Employee a WHERE a.email = :email AND a.password = :password"),
    @NamedQuery(
            name = "Employee.list",
            query = "SELECT a FROM Employee a")
})
public class Employee extends Account implements Serializable {

    private static final long serialVersionUID = 1L;
        
//    private boolean isAdmin;

    @OneToMany
    private List<Request> requests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//
//    public boolean isIsAdmin() {
//        return isAdmin;
//    }
//
//    public void setIsAdmin(boolean isAdmin) {
//        this.isAdmin = isAdmin;
//    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

//    public Set<String> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<String> roles) {
//        this.roles = roles;
//    }
    
    // TODO : zisti ako spravit dobry hashcode...

    @Override
    public String toString() {
        return "cz.muni.fi.tplevko.secureappexample.entity.Account[ id=" + id + " ]";
    }

}

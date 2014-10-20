package cz.muni.fi.tplevko.techsupport.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author tplevko
 */

@Entity
@NamedQueries({
    @NamedQuery(
            name = "Employee.find",
            query = "SELECT a FROM Employee a WHERE a.email = :email AND a.password = :password"),
    @NamedQuery(
            name = "Employee.list",
            query = "SELECT a FROM Employee a")
})
@Table(name = "EMPLOYEE")
public class Employee extends Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean isAdmin;

    @OneToMany(mappedBy = "assignee")
    private List<Request> requests;

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    // TODO : zisti ako spravit dobry hashcode a...
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.isAdmin ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.requests);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.isAdmin != other.isAdmin) {
            return false;
        }
        if (!Objects.equals(this.requests, other.requests)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.tplevko.secureappexample.entity.Employee[ id=" + id + " ]";
    }
}

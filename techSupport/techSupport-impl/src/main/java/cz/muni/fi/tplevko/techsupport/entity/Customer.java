package cz.muni.fi.tplevko.techsupport.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author tplevko
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Customer.find",
            query = "SELECT a FROM Customer a WHERE a.email = :email AND a.password = :password"),
    @NamedQuery(
            name = "Customer.list",
            query = "SELECT a FROM Customer a")
})
@Table(name = "CUSTOMER")
@PrimaryKeyJoinColumn(name="ID")
public class Customer extends Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "owner")
    private List<Request> requests;

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.requests);
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.requests, other.requests)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.tplevko.techsupport.entity.Customer[ id=" + id + " ]";
    }
}

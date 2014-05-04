package cz.muni.fi.tplevko.techsupport.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
            name = "Customer.find",
            query = "SELECT a FROM Customer a WHERE a.email = :email AND a.password = :password"),
    @NamedQuery(
            name = "Customer.list",
            query = "SELECT a FROM Customer a")
})
@Table(name = "CUSTOMER")
public class Customer extends Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Request> requests;

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.tplevko.techsupport.entity.Customer[ id=" + id + " ]";
    }
}

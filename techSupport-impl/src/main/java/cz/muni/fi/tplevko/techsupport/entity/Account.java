package cz.muni.fi.tplevko.techsupport.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ColumnTransformer;

/**
 * entity, that represents the account in the system. It contains all the basic
 * information, about the system user. It further diferentiates to customer and
 * employee.
 *
 * @author tplevko
 */
@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name="DISCRIMINATOR")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    /**
     * This inheritance strategy is documented here :
     * http://viralpatel.net/blogs/hibernate-inheritance-table-per-subclass-annotation-xml-mapping/
     *
     * The key generation strategy is not really good... should be changed, but
     * for now we will workaround it somehow...
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "ENTITY_SEQ",
            initialValue = 100, allocationSize = 1)
    @Column(name = "ID")
    protected Long id;

    @Column(length = 256, nullable = false, name = "firstName")
//    @ColumnTransformer(
//            read = "convert(varchar(60), DecryptByPassPhrase('firstName',firstName))",
//            write = "EncryptByPassPhrase('firstName',?)")
    private String firstName;

    @Column(length = 256, nullable = false, name = "lastName")
//    @ColumnTransformer(
//            //            read = "DecryptByPassPhrase('lastName', lastName)",
//            read = "convert(varchar(60), DecryptByPassPhrase('lastName',lastName))",
//            write = "EncryptByPassPhrase('lastName',?)")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    protected boolean active;

    @Column(name = "createdAt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    protected String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<Request> requestsOwned;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Request> getRequestsOwned() {
        return requestsOwned;
    }

    public void setRequestsOwned(List<Request> requestsOwned) {
        this.requestsOwned = requestsOwned;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.email);
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
        final Account other = (Account) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account{" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", active=" + active + ", createdAt=" + createdAt + '}';
    }
}

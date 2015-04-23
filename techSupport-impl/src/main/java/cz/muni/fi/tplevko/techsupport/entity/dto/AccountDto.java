package cz.muni.fi.tplevko.techsupport.entity.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tplevko
 */
public class AccountDto {

    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected boolean active;
    protected Date createdAt;
    protected String phoneNumber;
    private List<RequestDto> requestsOwned;
    
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

    public List<RequestDto> getRequestsOwned() {
        return requestsOwned;
    }

    public void setRequestsOwned(List<RequestDto> requestsOwned) {
        this.requestsOwned = requestsOwned;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.email);
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
        final AccountDto other = (AccountDto) obj;
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
        return "AccountDto{" + "id=" + id + ", firstName=" + firstName + ", lastName=" 
                + lastName + ", email=" + email + ", phone="+ phoneNumber +
                ", active=" + active + ", createdAt=" + createdAt + '}';
    }
}

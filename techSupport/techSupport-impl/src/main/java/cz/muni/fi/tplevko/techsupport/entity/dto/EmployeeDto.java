package cz.muni.fi.tplevko.techsupport.entity.dto;

import cz.muni.fi.tplevko.techsupport.entity.Request;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tplevko
 */
public class EmployeeDto extends AccountDto {

    private List<Request> requests;
    private boolean isAdmin;

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final EmployeeDto other = (EmployeeDto) obj;

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "EmployeeDto{" + "id=" + id + ", firstName=" + firstName +", lastName=" + lastName + ", email =" + email + '}';
    }
}

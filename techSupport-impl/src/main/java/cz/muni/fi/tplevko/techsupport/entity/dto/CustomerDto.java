package cz.muni.fi.tplevko.techsupport.entity.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author tplevko
 */
public class CustomerDto extends AccountDto {
    
    private List<RequestDto> requests;
    
    public List<RequestDto> getRequests() {
        return requests;
    }

    public void setRequests(List<RequestDto> requests) {
        this.requests = requests;
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
        final CustomerDto other = (CustomerDto) obj;

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "CustomerDto{" + "id=" + id + ", firstName=" + firstName +", lastName=" + lastName + ", email =" + email + '}';
    }

}

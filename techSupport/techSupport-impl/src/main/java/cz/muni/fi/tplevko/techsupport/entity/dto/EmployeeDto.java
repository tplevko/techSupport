package cz.muni.fi.tplevko.techsupport.entity.dto;

import cz.muni.fi.tplevko.techsupport.entity.Request;
import java.util.List;

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
}

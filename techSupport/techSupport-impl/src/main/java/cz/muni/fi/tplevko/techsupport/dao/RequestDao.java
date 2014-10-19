package cz.muni.fi.tplevko.techsupport.dao;

//import cz.muni.fi.tplevko.techsupport.entity.Customer;
//import cz.muni.fi.tplevko.techsupport.entity.Employee;
import cz.muni.fi.tplevko.techsupport.entity.Request;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface RequestDao {

    public void createRequest(Request request);

    public void updateRequest(Request request);

    public void deleteRequest(Request request);

    public Request findRequestById(Long id);
//
//    public List<Request> findRequestByCustomer(Customer owner);
//
//    public List<Request> findRequestByAssignee(Employee assignee);

    public List<Request> getAllRequests();
}

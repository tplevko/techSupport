package cz.muni.fi.tplevko.techsupport.dao;

import cz.muni.fi.tplevko.techsupport.entity.Request;
import java.util.List;

/**
 * Interface for the basic request DAO
 * 
 * @author tplevko
 */
public interface RequestDao {

    public void createRequest(Request request);

    public void updateRequest(Request request);

    public void deleteRequest(Request request);

    public Request findRequestById(Long id);

    public List<Request> getAllRequests();
    
    
    /**
     * this method is used, for getting all the requests, that
     * are still active, that means, which have not the flag set, 
     * to "executed = false"
     * 
     * @return 
     */
    public List<Request> getActiveRequests();
}

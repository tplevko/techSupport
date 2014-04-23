package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.entity.Customer;
import cz.muni.fi.tplevko.techsupport.entity.Employee;
import cz.muni.fi.tplevko.techsupport.entity.Request;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface RequestService {

    public void createRequest(RequestDto requestDto);

    public void updateRequest(RequestDto requestDto);

    public void deleteRequest(RequestDto requestDto);

    public RequestDto findProductById(Long id);

    public RequestDto findProductByOwner(Customer owner);

    public RequestDto findProductByAssignee(Employee assignee);

    public List<RequestDto> getAllRequests();

    public void assignPriority(Request request, long priority);

}

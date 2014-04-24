package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.entity.Request;
import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
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

    public RequestDto findRequestById(Long id);

    public RequestDto findRequestByOwner(CustomerDto owner);

    public RequestDto findRequestByAssignee(EmployeeDto assignee);

    public List<RequestDto> getAllRequests();

    public void assignPriority(Request request, long priority);

}

package cz.muni.fi.tplevko.techsupport.services;

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

    public List<RequestDto> getAllRequests();
    
    /**
     * this method is used, for getting only the active requests,
     * 
     * @return 
     */
    public List<RequestDto> getActiveRequests();

}

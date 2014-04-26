package cz.muni.fi.tplevko.techsupport.entity.dto;

import java.util.List;

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

}

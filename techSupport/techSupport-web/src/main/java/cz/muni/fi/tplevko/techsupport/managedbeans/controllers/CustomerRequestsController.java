package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.RequestService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "customerRequestsController")
@ManagedBean
@Scope("session")
public class CustomerRequestsController implements Serializable {

    private Long requestId;

    private static final long serialVersionUID = 1L;
    private List<RequestDto> customerRequestList;
    private RequestDto selectedRequestDto;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RequestService requestService;

    @PostConstruct
    public void init() {
        customerRequestList = new ArrayList<RequestDto>();
    }

    public List<RequestDto> getCustomerRequestList() {

        String currentCustomer = SecurityUtils.getSubject().getPrincipal().toString();
        CustomerDto customer = customerService.findCustomerByEmail(currentCustomer);

        customerRequestList = customer.getRequests();

        return customerRequestList;
    }

    // TODO : this is insecure... it retrieves even the requestDto-s, that don't
    // belong to the current customer... 
    // it has to be repaired..
    public void editRequestBefore() {

        selectedRequestDto = requestService.findRequestById(requestId);
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public RequestDto getSelectedRequestDto() {
        return selectedRequestDto;
    }

    public void setSelectedRequestDto(RequestDto selectedRequestDto) {
        this.selectedRequestDto = selectedRequestDto;
    }

    public void deselect() {
        selectedRequestDto = null;
    }

    public void rowKeyListener(Object rowKey) {

        Long id = Long.valueOf((String) rowKey);

        selectedRequestDto = requestService.findRequestById(id);

    }

}

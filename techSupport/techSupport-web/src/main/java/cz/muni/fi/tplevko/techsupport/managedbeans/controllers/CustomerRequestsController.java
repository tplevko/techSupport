package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.RequestService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
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

    public String editRequestBefore() {

        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long requestId = Long.valueOf(parameterMap.get("requestId"));
        selectedRequestDto = requestService.findRequestById(requestId);

        return "/request/requestDetail?faces-redirect=true";
    }

    public RequestDto getRequestDto() {
        return selectedRequestDto;
    }

    public void setRequestDto(RequestDto requestDto) {
        this.selectedRequestDto = requestDto;
    }

    public void deselect() {
        selectedRequestDto = null;
    }

    public void rowKeyListener(Object rowKey) {

        Long id = Long.valueOf((String) rowKey);

        selectedRequestDto = requestService.findRequestById(id);

    }

}

package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
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

    private static final long serialVersionUID = 1L;
    private List<RequestDto> customerRequestList;

    @PostConstruct
    public void init() {
        customerRequestList = new ArrayList<RequestDto>();
    }

    @Autowired
    private CustomerService customerService;

    public List<RequestDto> getCustomerRequestList() {

        String currentCustomer = SecurityUtils.getSubject().getPrincipal().toString();
        CustomerDto customer = customerService.findCustomerByEmail(currentCustomer);
        
        customerRequestList = customer.getRequests();
        
        return customerRequestList;
    }

    
}

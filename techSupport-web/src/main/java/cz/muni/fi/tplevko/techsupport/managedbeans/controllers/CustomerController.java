package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "customerController")
@ManagedBean
@Scope("session")
public class CustomerController {

    private static final Logger LOG = Logger.getLogger(CustomerController.class.getName());

    @Autowired
    private CustomerService customerService;

    private CustomerDto customer;
    private List<CustomerDto> customerList;
    private CustomerDto selectedItem;

    private String userEmail;

    @PostConstruct
    public void init() {

        customer = new CustomerDto();
        customerList = new ArrayList<CustomerDto>();
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<CustomerDto> getCustomerList() {

        customerList = customerService.getAllCustomers();

        return customerList;
    }

    public String listCustomers() {

        customerList = customerService.getAllCustomers();
        return "/employee/admin/customer/customerList?faces-redirect=true";
    }

    public void setCustomerList(List<CustomerDto> customerList) {
        this.customerList = customerList;
    }

    // TODO : editovanie asi nebude potrebne...
    public String deleteCustomer() {

//        customer.setName(productName);
//
//        customerService.updateProduct(customer);
//
//        return "/product/productList?faces-redirect=true";
        return "/employee/admin/customer/customerList?faces-redirect=true";
    }

    public String editCustomerInfo() {

        return null;
    }

    public String editCustomerBefore() {

        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long customerId = Long.valueOf(parameterMap.get("customerId"));
        customer = customerService.findCustomerById(customerId);

        return "/employee/admin/customer/editCustomer?faces-redirect=true";
    }

    public CustomerDto getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(CustomerDto selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void deselect() {
        selectedItem = null;
    }

    public void rowKeyListener(Object rowKey) {

        // TODO : debug...
        Long id = Long.valueOf((String) rowKey);
//
//        if (selectedItem != null
//                && selectedItem.getId() == id) {
//
//            deselect();
//        } else {

//        deselect();
        selectedItem = customerService.findCustomerById(id);
//        }

    }

}

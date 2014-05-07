package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.Customer;
import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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

    @Autowired
    private CustomerService customerService;

    private CustomerDto customer;
    private List<CustomerDto> customerList;

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

    public String activeteCustomer(CustomerDto customerToActivate) {
//
//        ProductDto newProduct = new ProductDto();
//
//        newProduct.setName(productName);
//
//        customerService.createProduct(newProduct);
//
//        return "/employee/admin/product/productList?faces-redirect=true";
        return null;
    }

    public String deactivateCustomer() {
//
//        customerService.deleteProduct(customer);
//
//        return "/employee/admin/product/productList?faces-redirect=true";
        return null;
    }

    // TODO : editovanie asi nebude potrebne...
    public String deleteCustomer() {

//        customer.setName(productName);
//
//        customerService.updateProduct(customer);
//
//        return "/product/productList?faces-redirect=true";
        return null;
    }
    
    public String editCustomerInfo() {
        
        return null;
    }

}

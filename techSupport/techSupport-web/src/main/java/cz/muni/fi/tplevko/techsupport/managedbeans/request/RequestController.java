package cz.muni.fi.tplevko.techsupport.managedbeans.request;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.ProductService;
import cz.muni.fi.tplevko.techsupport.services.RequestService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component
@ManagedBean
@Scope("session")
public class RequestController implements Serializable {

    private static final Long DEFAULT_PRIORITY = 1L;
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(RequestController.class.getName());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ProductService productService;

    private RequestDto request;
    private List<RequestDto> requestList;
    private List<ProductDto> productList;
    private ProductDto selectedproduct;

    @PostConstruct
    public void init() {

        request = new RequestDto();
        selectedproduct = new ProductDto();
        requestList = new ArrayList<RequestDto>();
        productList = new ArrayList<ProductDto>();
    }

    public RequestDto getRequest() {
        return request;
    }

    public void setRequest(RequestDto request) {
        this.request = request;
    }

    public ProductDto getSelectedproduct() {
        return selectedproduct;
    }

    public void setSelectedproduct(ProductDto selectedproduct) {
        this.selectedproduct = selectedproduct;
    }

    public List<RequestDto> getRequestList() {

        requestList = requestService.getAllRequests();

        return requestList;
    }

    public String listRequests() {

        requestList = requestService.getAllRequests();
        return "/employee/technician/requestList?faces-redirect=true";
    }

    public void setRequestList(List<RequestDto> requestList) {
        this.requestList = requestList;
    }

    public List<ProductDto> getProductList() {

        productList = productService.getAllProducts();
        return productList;
    }

    public void setProductList(List<ProductDto> productList) {
        this.productList = productList;
    }

    public String newRequest() {

        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();

        CustomerDto customer = customerService.findCustomerByEmail(currentUser);

        LOG.info("***** the user : " + customer.getFirstName() + " " + customer.getLastName() + " *****");

        request.setPriority(DEFAULT_PRIORITY);
        request.setOwner(customer);

        LOG.info("***** the product : " + selectedproduct.getName() + " " + selectedproduct.getId() + " *****");

        request.setProduct(selectedproduct);
        request.setPriority(selectedproduct.getDefaultPriority());
        requestService.createRequest(request);

        return "/thankYou?faces-redirect=true";
    }

    public String addRequestBefore() {
        request = new RequestDto();
        return "/request/createRequest?faces-redirect=true";
    }

//
//    // TODO : editovanie asi nebude potrebne...
//    public String editProduct() {
//
//        request.setName(productName);
//
//        requestService.updateProduct(request);
//
//        return "/product/productList?faces-redirect=true";
//    }
//
//    public List<ProductDto> getProductsList() {
//
//        return requestService.getAllProducts();
//    }
}

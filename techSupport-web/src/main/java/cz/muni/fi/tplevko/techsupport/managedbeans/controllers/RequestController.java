package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import cz.muni.fi.tplevko.techsupport.services.ProductService;
import cz.muni.fi.tplevko.techsupport.services.RequestService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "requestController")
@Scope("session")
public class RequestController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(RequestController.class.getName());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ProductService productService;

    private RequestDto request;
    private List<RequestDto> requestList;
    private List<ProductDto> productList;
    private ProductDto selectedproduct;
    private Collection<Object> selected;
    private RequestDto selectedItem;
    private Long selectedItemId;
    // This requestId is used with employee updating some request, or commenting on it
    private Long requestId;

    @PostConstruct
    public void init() {

        request = new RequestDto();
        selectedproduct = new ProductDto();
        requestList = new ArrayList<RequestDto>();
        productList = new ArrayList<ProductDto>();
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(Long selectedItemId) {
        this.selectedItemId = selectedItemId;
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

        return requestList;
    }

    public String listActiveRequests() {

        requestList = requestService.getActiveRequests();
        return "/employee/technician/requests/requestList?faces-redirect=true";
    }

    public String listRequests() {
        requestList = requestService.getAllRequests();
//                requestList = requestService.getActiveRequests();
        return "/employee/technician/requests/requestList?faces-redirect=true";
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

        request.setOwner(customer);
        request.setProduct(selectedproduct);
        request.setPriority(selectedproduct.getDefaultPriority());
        requestService.createRequest(request);
        request = new RequestDto();
        
        return "/thankYou?faces-redirect=true";
    }

    public String addRequestBefore() {
        request = new RequestDto();
        return "/request/createRequest?faces-redirect=true";
    }

    public void editRequestBefore() {

        request = requestService.findRequestById(requestId);
    }

//    public String editRequest() {
//
//        String currentEmployee = SecurityUtils.getSubject().getPrincipal().toString();
//
//        EmployeeDto employee = employeeService.findEmployeeByEmail(currentEmployee);
//
//        if (request.isExecuted() == true) {
//
//            request.setFinished(new Date());
//            request.setAssignee(employee);
//
//        } else {
//            request.setFinished(null);
//            request.setAssignee(null);
//        }
//
//        requestService.updateRequest(request);
//
//        return "/employee/technician/requests/requestList?faces-redirect=true";
//    }
    public String deleteRequest() {

        RequestDto requestToDelete = requestService.findRequestById(selectedItemId);
        requestService.deleteRequest(requestToDelete);

        // refresh request list
        // TODO : this won't work for all the request lists...
        requestList.remove(requestToDelete);

        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true&includeViewParams=true";
    }

    public Collection<Object> getSelected() {
        return selected;
    }

    public void setSelected(Collection<Object> selected) {
        this.selected = selected;
    }

    public RequestDto getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(RequestDto selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void deselect() {
        selectedItem = null;
    }

    public void rowKeyListener(Object rowKey) {

        // TODO : debug...
//        LOG.info("***** the row key value is : " + rowKey + " *****");
        Long id = Long.valueOf((String) rowKey);
//
//        if (selectedItem != null
//                && selectedItem.getId() == id) {
//
//            deselect();
//        } else {

        deselect();
        selectedItem = requestService.findRequestById(id);
//        }
        LOG.info("***** the row key value is : " + selectedItem.getText() + " *****");

    }
}

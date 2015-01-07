package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import cz.muni.fi.tplevko.techsupport.services.RequestService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "editRequestController")
@Scope("session")
public class EditRequestController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(EditRequestController.class.getName());

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RequestService requestService;

    private Long requestId;
    private RequestDto selectedRequest;
    private List<EmployeeDto> employeeList;
    private EmployeeDto selectedEmployee;
    private Subject currentUser;

    @PostConstruct
    public void init() {

        currentUser = SecurityUtils.getSubject();
    }

    public void initRequest() {
        employeeList = employeeService.getAllEmployees();
        selectedRequest = requestService.findRequestById(requestId);
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public RequestDto getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(RequestDto selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public EmployeeDto getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(EmployeeDto selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<EmployeeDto> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeDto> employeeList) {
        this.employeeList = employeeList;
    }

    public String editRequest() {
        currentUser.isAuthenticated();
        String currentEmployee = SecurityUtils.getSubject().getPrincipal().toString();

        EmployeeDto employee = employeeService.findEmployeeByEmail(currentEmployee);

        if (selectedRequest.isExecuted() == true) {

            selectedRequest.setFinished(new Date());
            selectedRequest.setAssignee(employee);

        } else {
            selectedRequest.setFinished(null);
            selectedRequest.setAssignee(null);
        }

        requestService.updateRequest(selectedRequest);
        // the changed state is written into LOG, so it can be reviewed afterwards.
        LOG.info("request update made by : " + currentEmployee);

        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true&includeViewParams=true";

    }

}

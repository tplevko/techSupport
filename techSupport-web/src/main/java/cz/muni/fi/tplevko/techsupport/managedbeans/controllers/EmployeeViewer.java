//package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;
//
//import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
//import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author tplevko
// */
//@Component(value = "employeeViewer")
////@ManagedBean
//@Scope("session")
//public class EmployeeViewer implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    private static final Logger LOG = Logger.getLogger(EmployeeViewer.class.getName());
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    private EmployeeDto employeeDto;
//    private List<EmployeeDto> employeeList;
//    private Long currentEmployeeIndex;
//
//    private EmployeeDto selectedItem;
//
//    @PostConstruct
//    public void init() {
//
//        employeeDto = new EmployeeDto();
//        employeeList = new ArrayList<EmployeeDto>();
//    }
//
//    public Long getCurrentEmployeeIndex() {
//        return currentEmployeeIndex;
//    }
//
//    public void setCurrentEmployeeIndex(Long currentEmployeeIndex) {
//        this.currentEmployeeIndex = currentEmployeeIndex;
//    }
//
//    public EmployeeDto getEmployeeDto() {
//
//        return employeeDto;
//    }
//
//    public EmployeeDto getEmployeeDtoById(Long id) {
//        employeeDto = employeeService.findEmployeeById(id);
//        return employeeDto;
//    }
//
//    public void setEmployeeDto(EmployeeDto employeeDto) {
//        this.employeeDto = employeeDto;
//    }
//
//    public List<EmployeeDto> getEmployeeList() {
//        employeeList = employeeService.getAllEmployees();
//        return employeeList;
//    }
//
//    public void setEmployeeList(List<EmployeeDto> employeeList) {
//        this.employeeList = employeeList;
//    }
//
//    public String listEmployees() {
//
//        employeeList = employeeService.getAllEmployees();
//        return "/employee/admin/employee/employeeList?faces-redirect=true";
//    }
//
//    public EmployeeDto getSelectedItem() {
//        return selectedItem;
//    }
//
//    public void setSelectedItem(EmployeeDto selectedItem) {
//        this.selectedItem = selectedItem;
//    }
//
//    public void deselect() {
//        selectedItem = null;
//    }
//
//    public void rowKeyListener(Object rowKey) {
//
//        // TODO : debug...
//        LOG.info("***** the row key value is : " + rowKey + " *****");
//        Long id = Long.valueOf((String) rowKey);
////
////        if (selectedItem != null
////                && selectedItem.getId() == id) {
////
////            deselect();
////        } else {
//
////        deselect();
//        
//        selectedItem = employeeService.findEmployeeById(id);
////        }
//        LOG.info("***** the row key value is : " + selectedItem.getEmail() + " *****");
//
//    }
//
//}

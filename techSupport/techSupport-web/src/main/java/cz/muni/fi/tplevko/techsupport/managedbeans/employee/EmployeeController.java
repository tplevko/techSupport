package cz.muni.fi.tplevko.techsupport.managedbeans.employee;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.richfaces.component.UIExtendedDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component
@ManagedBean
@Scope("session")
public class EmployeeController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());

    @Autowired
    private EmployeeService employeeService;

    private EmployeeDto employeeDto;
    private List<EmployeeDto> employeeList;
    private String localePattern;

    private Collection<Object> selected;
    private EmployeeDto selectedItem;

    @PostConstruct
    public void init() {

        employeeDto = new EmployeeDto();
        employeeList = new ArrayList<EmployeeDto>();
    }
//
//    public EmployeeDto getEmployeeDto(long employeeId) {
//
//        employeeDto = employeeService.findEmployeeById(employeeId);
//        return employeeDto;
//    }
//    

    public EmployeeDto getEmployeeDto() {

        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public List<EmployeeDto> getEmployeeList() {
        employeeList = employeeService.getAllEmployees();
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeDto> employeeList) {
        this.employeeList = employeeList;
    }

    // TODO : ADMIN richts
    public String removeEmployee() {

        employeeService.deleteEmployee(employeeDto);
        deselect();
        // TODO : make an method for navigation rules...
        return "/employee/admin/employee/employeeList?faces-redirect=true";
    }

    public void deactivateEmployee() {

    }

//    
//    public String createEmployee() {
//        
//        // TODO : create automatic passwd generation... 
//        
//        employeeDto.setPassword("passwd");
//        
//        employeeService.createEmployee(employeeDto);
//        
//        return "";
//    }
    public String getLocalePattern() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        final DateFormat dateInstance
                = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        final DateFormat timeInstance = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
        localePattern = ((SimpleDateFormat) dateInstance).toPattern()
                + " " + ((SimpleDateFormat) timeInstance).toPattern();
        return localePattern;
    }

    public void setLocalePattern(String localePattern) {
        this.localePattern = localePattern;
    }

    public Collection<Object> getSelected() {
        return selected;
    }

    public String editEmployeeBefore() {

        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long employeeId = Long.valueOf(parameterMap.get("matchId"));
        employeeDto = employeeService.findEmployeeById(employeeId);
//        return "/SoccerMatch/editSoccerMatch?faces-redirect=true";
        return null;
    }

    public String editMatch() {

        employeeService.updateEmployee(employeeDto);
//        resetMatch();

//        return listMatches();
        return "/employee/admin/employee/employeeList?faces-redirect=true";

    }

    public String deleteMatch(Long matchId) {
        employeeDto = employeeService.findEmployeeById(matchId);
        employeeService.deleteEmployee(employeeDto);
//        return listMatches();
        return "/employee/admin/employee/employeeList?faces-redirect=true";

    }

    public void setSelected(Collection<Object> selected) {
        this.selected = selected;
    }

    public EmployeeDto getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(EmployeeDto selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void deselect() {
        selectedItem = null;
    }
//
//    public void selectionListener(AjaxBehaviorEvent event) {
//        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
//        Object originalKey = dataTable.getRowKey();
//        for (Object selectionKey : selected) {
//            dataTable.setRowKey(selectionKey);
//            if (dataTable.isRowAvailable()) {
//                selectedItem = ((EmployeeDto) dataTable.getRowData());
//            }
//        }
//        dataTable.setRowKey(originalKey);
//    }
//    
//    
    public void rowKeyListener(Object rowKey) {
        
        // TODO : debug...
        LOG.info("***** the row key value is : " + rowKey  + " *****");
        
        Long id = Long.valueOf((String) rowKey);

        if (selectedItem != null
                && selectedItem.getId() == id) {

            deselect();
        } else {

            deselect();
            selectedItem = employeeService.findEmployeeById(id);
        }
         LOG.info("***** the row key value is : " + selectedItem.getEmail()  + " *****");

    }
    
}

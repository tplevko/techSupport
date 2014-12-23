package cz.muni.fi.tplevko.techsupport.managedbeans.converter;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * converter used with request editing, when the employee is assigned to
 * particullar problem.
 *
 * @author tplevko
 */
@Component(value = "employeeNameConverter")
@Scope("request")
public class EmployeeNameConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(EmployeeNameConverter.class.getName());

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.equals("")) {
            return null;
        }

        EmployeeDto employee = employeeService.findEmployeeByEmail(value);
        return employee;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return null;
        }
        
        EmployeeDto employee = new EmployeeDto();
        employee = (EmployeeDto) value;
        return employee.getEmail();
    }
}

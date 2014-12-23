package cz.muni.fi.tplevko.techsupport.managedbeans.converter;

import cz.muni.fi.tplevko.techsupport.entity.dto.EmployeeDto;
import cz.muni.fi.tplevko.techsupport.services.EmployeeService;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component("employeeConverter")
public class EmployeeConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(EmployeeConverter.class.getName());

    @Autowired(required = true)
    private EmployeeService employeeService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    
        if (value == null || value.equals("")) {
            return null;
        }
        Long id = Long.parseLong(value);

        
        LOG.info("***** converting the value : " + id + " *****");
                
        EmployeeDto empl = employeeService.findEmployeeById(id);
        
        return empl;
    }

    String getStringKey(Long value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        LOG.info("***** converting the value : *****");

        if (value == null) {
            return null;
        }
        
        if (value instanceof EmployeeDto) {

            EmployeeDto employee = (EmployeeDto) value;
                 
            LOG.info("***** employee : " + employee.getEmail() + " *****");

            return getStringKey(employee.getId());

        } else {
            throw new IllegalArgumentException("object " + value + " is of type " + value.getClass().getName() + "; expected type: " + EmployeeDto.class.getName());
        }
    }

}

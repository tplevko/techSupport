package cz.muni.fi.tplevko.techsupport.managedbeans.converter;

import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.services.RequestService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 *
 * @author tplevko
 */
@Component(value = "requestConverter")
@Scope("request")
public class RequestConverter implements Converter {

    @Autowired
    private RequestService requestService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            return null;
        }
        Long id = Long.parseLong(value);

        return requestService.findRequestById(id);
    }

    String getStringKey(Long value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return null;
        }
        if (value instanceof RequestDto) {

            RequestDto request = (RequestDto) value;
            return getStringKey(request.getId());

        } else {
            throw new IllegalArgumentException("object " + value + " is of type " + value.getClass().getName() + "; expected type: " + RequestDto.class.getName());
        }
    }

    
}

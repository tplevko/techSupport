//package cz.muni.fi.tplevko.techsupport.managedbeans.converter;
//
//import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
//import cz.muni.fi.tplevko.techsupport.services.RequestService;
//import java.util.logging.Logger;
//import javax.faces.bean.ManagedBean;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author tplevko
// */
//@ManagedBean
//@Component(value = "requestConverter")
//public class RequestConverter implements Converter {
//
//    private static final Logger LOG = Logger.getLogger(RequestConverter.class.getName());
//
//    @Autowired(required = true)
//    private RequestService requestService;
//
//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value == null || value.equals("")) {
//            return null;
//        }
//
//        Long id = Long.parseLong(value);
//
//        RequestDto req = requestService.findRequestById(id);
//             
//        LOG.info("***** the req value is: " + req.toString() + " *****");
//        
//        return req;
//    }
//
//    String getStringKey(Long value) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(value);
//        return sb.toString();
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//
//        if (value == null) {
//            return null;
//        }
//        if (value instanceof RequestDto) {
//
//            RequestDto request = (RequestDto) value;
//            LOG.info("***** the request value is: " + request.getText() + " *****");
//
//            return getStringKey(request.getId());
//
//        } else {
//            throw new IllegalArgumentException("object " + value + " is of type " + value.getClass().getName() + "; expected type: " + RequestDto.class.getName());
//        }
//    }
//
//}

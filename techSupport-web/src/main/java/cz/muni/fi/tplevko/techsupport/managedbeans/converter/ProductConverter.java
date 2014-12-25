package cz.muni.fi.tplevko.techsupport.managedbeans.converter;

import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import cz.muni.fi.tplevko.techsupport.services.ProductService;
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
@Scope("request")
@Component(value = "productConverter")
public class ProductConverter implements Converter {
    
    @Autowired
    private ProductService productService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            return null;
        }
        Long id = Long.parseLong(value);

        return productService.findProductById(id);
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
        if (value instanceof ProductDto) {

            ProductDto product = (ProductDto) value;
            return getStringKey(product.getId());

        } else {
            throw new IllegalArgumentException("object " + value + " is of type " + value.getClass().getName() + "; expected type: " + ProductDto.class.getName());
        }
    }
}


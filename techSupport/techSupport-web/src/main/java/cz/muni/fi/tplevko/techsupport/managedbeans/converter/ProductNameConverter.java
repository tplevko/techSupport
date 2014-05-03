package cz.muni.fi.tplevko.techsupport.managedbeans.converter;

import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import cz.muni.fi.tplevko.techsupport.services.ProductService;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@ManagedBean
@RequestScoped
//@FacesConverter(value = "productNameConverter")
//@Component
//@Scope("request")
public class ProductNameConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(ProductNameConverter.class.getName());

    @Autowired
    private ProductService productService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.equals("")) {
            return null;
        }
        return productService.findProductByName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//
//        if (value == null) {
//            return null;
//        }
//        if (value instanceof ProductDto) {

        LOG.info("***** the inserted value is: " + value + " *****");

        ProductDto product = new ProductDto();

        product = (ProductDto) value;

        LOG.info("***** converted the value : " + product.toString()+ " *****");

        return product.getName();

//
//        } else {
//            throw new IllegalArgumentException("object " + value + " is of type " + value.getClass().getName() + "; expected type: " + ProductDto.class.getName());
//        }
    }

}

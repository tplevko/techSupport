package cz.muni.fi.tplevko.techsupport.managedbeans.converter;

import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import cz.muni.fi.tplevko.techsupport.services.ProductService;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * this converter is used with the dropdown menu, for choosing the product of
 * particular request, by customer.
 *
 * @author tplevko
 */
@Component(value = "productNameConverter")
@Scope("request")
public class ProductNameConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(ProductNameConverter.class.getName());

    @Autowired
    private ProductService productService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.equals("")) {
            return null;
        }
        ProductDto product = productService.findProductByName(value);

        return product;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return null;
        }

        ProductDto product = new ProductDto();

        product = (ProductDto) value;

        return product.getName();
    }

}

package cz.muni.fi.tplevko.techsupport.managedbeans.product;

import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import cz.muni.fi.tplevko.techsupport.services.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component
@ManagedBean
@Scope("request")
public class ProductsController {

    @Autowired
    private ProductService productService;

    private ProductDto product;
    private List<ProductDto> productList;

    @PostConstruct
    public void init() {

        product = new ProductDto();
        productList = new ArrayList<ProductDto>();
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public String insertProduct() {

        productService.createProduct(product);

        return "/employee/admin/product/productList?faces-redirect=true";
    }

    public String addProductBefore() {

        product = new ProductDto();

        return "/employee/admin/product/newProduct?faces-redirect=true";
    }

    public String listProducts() {

        productList = productService.getAllProducts();
        return "/employee/admin/product/productList?faces-redirect=true";
    }

    public String deleteProduct() {

        productService.deleteProduct(product);

        return "/employee/admin/product/productList?faces-redirect=true";
    }

    // TODO : editovanie asi nebude potrebne...
    public String editProduct() {

        productService.updateProduct(product);

        return "/product/productList?faces-redirect=true";
    }

    public List<ProductDto> getProductsList() {

        return productService.getAllProducts();
    }
}

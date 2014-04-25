package cz.muni.fi.tplevko.techsupport.managedbeans.product;

import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import cz.muni.fi.tplevko.techsupport.services.ProductService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
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
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public String insertProduct() {

        ProductDto newProduct = new ProductDto();

        newProduct.setName(productName);

        productService.createProduct(newProduct);
        
        return "/product/productList?faces-redirect=true";
    }

    public String deleteProduct() {
        
        productService.deleteProduct(product);
        
        return "/product/productList?faces-redirect=true";
    }
    // TODO : editovanie asi nebude potrebne...
    public String editProduct() {
        
        product.setName(productName);
        
        productService.updateProduct(product);
        
        return "/product/productList?faces-redirect=true";
    }

    public List<ProductDto> getProductsList() {

       return productService.getAllProducts();
    }
}

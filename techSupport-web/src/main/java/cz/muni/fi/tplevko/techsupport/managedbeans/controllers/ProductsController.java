package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import cz.muni.fi.tplevko.techsupport.services.ProductService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "productsController")
@ManagedBean
@Scope("session")
public class ProductsController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ProductsController.class.getName());

    @Autowired
    private ProductService productService;

    private ProductDto product;
    private List<ProductDto> productList;
    private Long prodId;

    @PostConstruct
    public void init() {

        product = new ProductDto();
        productList = new ArrayList<ProductDto>();
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
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

        ProductDto prodToBeDeleted = productService.findProductById(prodId);

        productService.deleteProduct(prodToBeDeleted);

        return "/employee/admin/product/productList?faces-redirect=true";
    }

    public String editProduct() {

        productService.updateProduct(product);

        return "/employee/admin/product/productList?faces-redirect=true";
    }

    public String editProductBefore() {

        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long productId = Long.valueOf(parameterMap.get("productId"));
        product = productService.findProductById(productId);

        return "/employee/admin/product/editProduct?faces-redirect=true";    }

    public List<ProductDto> getProductsList() {

        return productService.getAllProducts();
    }

}

package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import cz.muni.fi.tplevko.techsupport.services.ProductService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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

    private Collection<Object> selected;
    private ProductDto selectedItem;
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

    public Collection<Object> getSelected() {
        return selected;
    }

    public void setSelected(Collection<Object> selected) {
        this.selected = selected;
    }

    public ProductDto getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(ProductDto selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void deselect() {
        selectedItem = null;
    }

    public void rowKeyListener(Object rowKey) {

        // TODO : debug...
        LOG.info("***** the row key value is : " + rowKey + " *****");
        Long id = Long.valueOf((String) rowKey);
//
//        if (selectedItem != null
//                && selectedItem.getId() == id) {
//
//            deselect();
//        } else {

        deselect();
        selectedItem = productService.findProductById(id);
//        }
        LOG.info("***** the row key value is : " + selectedItem.getName() + " *****");

    }
}

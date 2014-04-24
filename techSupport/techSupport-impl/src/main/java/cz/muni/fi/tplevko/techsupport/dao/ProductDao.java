package cz.muni.fi.tplevko.techsupport.dao;

import cz.muni.fi.tplevko.techsupport.entity.Product;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface ProductDao {

    public void createProduct(Product product);

    public void updateProduct(Product product);

    public void deleteProduct(Product product);

    public Product findProductById(Long id);

    public Product findProductByName(String name);

    public List<Product> getAllProduct();
}

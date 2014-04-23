package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface ProductService {

    public void createProduct(ProductDto productDto);

    public void updateProduct(ProductDto productDto);

    public void deleteProduct(ProductDto productDto);

    public ProductDto findProductById(Long id);

    public ProductDto findProductByName(String name);

    public List<ProductDto> getAllProducts();

    public void activateProductAccount(ProductDto productDto);
}

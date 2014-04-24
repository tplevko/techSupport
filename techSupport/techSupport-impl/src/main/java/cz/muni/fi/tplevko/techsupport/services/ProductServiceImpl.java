package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.dao.ProductDao;
import cz.muni.fi.tplevko.techsupport.entity.Product;
import cz.muni.fi.tplevko.techsupport.entity.dto.ProductDto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tplevko
 */
@Service(value = "productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    // Use logger
    private static final Logger log = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    //@Qualifier(value = "customerDao")
    private ProductDao productDao;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public void createProduct(ProductDto productDto) {

        if (productDto == null) {
            throw new IllegalArgumentException("Product to be created is null");
        }

        if (productDto.getId() != null) {
            throw new IllegalArgumentException("Product has have id set");
        }

        Product product = mapper.map(productDto, Product.class);

        productDao.createProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(ProductDto productDto) {

        if (productDto == null) {
            throw new IllegalArgumentException("Employee to be created is null");
        }

        if (productDto.getId() == null) {
            throw new IllegalArgumentException("Employee has not set id");
        }

        Product product = mapper.map(productDto, Product.class);
        productDao.updateProduct(product);
    }

    @Override
    @Transactional
    public void deleteProduct(ProductDto productDto) {
        if (productDto == null) {
            throw new IllegalArgumentException("Product to be created is null");
        }

        if (productDto.getId() == null) {
            throw new IllegalArgumentException("Product has not set id");
        }

        Product product = mapper.map(productDto, Product.class);
        productDao.deleteProduct(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findProductById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        Product product = productDao.findProductById(id);

        return mapper.map(product, ProductDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findProductByName(String name) {
        
        if (name == null) {
            throw new IllegalArgumentException("name can't be nulll");
        }

        Product product = productDao.findProductByName(name);

        return mapper.map(product, ProductDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {

        List<Product> products = productDao.getAllProduct();

        List<ProductDto> productsReturn = new ArrayList<>();
        for (Product p : products) {
            productsReturn.add(mapper.map(p, ProductDto.class));
        }
        return productsReturn;
    }
}

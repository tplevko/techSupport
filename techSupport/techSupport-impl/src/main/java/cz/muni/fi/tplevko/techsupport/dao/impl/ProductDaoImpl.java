package cz.muni.fi.tplevko.techsupport.dao.impl;

import cz.muni.fi.tplevko.techsupport.dao.ProductDao;
import cz.muni.fi.tplevko.techsupport.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tplevko
 */
@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    @Override
    public void createProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Product to be created is null");
        }
        if (product.getId() != null) {
            throw new IllegalArgumentException("Product to be created has already assigned id");
        }

        validateProduct(product);
        em.persist(product);
    }

    @Override
    public void updateProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Product to be updated is null");
        }
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product to be updated has null id");
        }
        if (findProductById(product.getId()) == null) {
            throw new IllegalArgumentException("Product to be updated doesn't exist in DB");
        }

        validateProduct(product);
        em.merge(product);
    }

    @Override
    public void deleteProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("product to be removed is null");
        }
        if (product.getId() == null) {
            throw new IllegalArgumentException("product to be removed has not assigned id");
        }

        validateProduct(product);
        em.remove(product);
    }

    @Override
    public Product findProductById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Product id to be retrieved is null");
        }

        Product result = null;
        result = em.find(Product.class, id);
        return result;
    }

    @Override
    public Product findProductByName(String name) {

        Product product = null;
        final String qstring = "SELECT e FROM Product e WHERE e.name = :name";
        try {

            TypedQuery<Product> query = em.createQuery(qstring, Product.class);
            query.setParameter("name", name);
            product = query.getSingleResult();
            return product;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Product> getAllProduct() {

        List<Product> products = new ArrayList<>();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Product.class));
        Query q = em.createQuery(cq);
        products = q.getResultList();
        return products;
    }

    // TODO : revise.. 
    private void validateProduct(Product product) {
        if (product.getName() == null) {
            throw new IllegalArgumentException("Product name must be set, it's null");
        }
        if (product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name must be set, it's empty");
        }

    }

}

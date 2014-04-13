package cz.muni.fi.tplevko.secureappexample.dao.impl;

import cz.muni.fi.tplevko.secureappexample.dao.InvoiceDao;
import cz.muni.fi.tplevko.secureappexample.entity.Invoice;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tplevko
 */
@Repository(value = "orderDao")
public class InvoiceDaoImpl implements InvoiceDao {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createOrder(Invoice order) {

        if (order == null) {
            throw new IllegalArgumentException("Order to be created is null");
        }
        if (order.getId() != null) {
            throw new IllegalArgumentException("Order to be created has already assigned id");
        }

        validateOrder(order);
        em.persist(order);
    }

    @Override
    public void deleteOrder(Invoice order) {

        if (order == null) {
            throw new IllegalArgumentException("Order to be created is null");
        }
        if (order.getId() == null) {
            throw new IllegalArgumentException("Order to be deleted has not assigned id");
        }

        validateOrder(order);
        em.remove(order);
    }

    @Override
    public void updateOrder(Invoice order) {

        if (order == null) {
            throw new IllegalArgumentException("Order to be updated is null");
        }
        if (order.getId() == null) {
            throw new IllegalArgumentException("Order to be updated has null id");
        }
        if (findOrderById(order.getId()) == null) {
            throw new IllegalArgumentException("Order to be updated doesn't exist in DB");
        }

        validateOrder(order);
        em.merge(order);
    }

    @Override
    public Invoice findOrderById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Order id to be retrieved is null");
        }

        Invoice result = null;
        result = em.find(Invoice.class, id);
        return result;

    }

    @Override
    public List<Invoice> getAllOrders() {

        List<Invoice> accounts = new ArrayList<>();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Invoice.class));
        Query q = em.createQuery(cq);
        accounts = q.getResultList();
        return accounts;
    }

    private void validateOrder(Invoice order) {
//        if (order.getCalendar() == null) {
//            throw new IllegalArgumentException("Order callendar is null... it should not be null...");
//        }

        if (order.getItems() == null) {
            throw new IllegalArgumentException("Order has to have the items set");
        }

        if (order.getOwner() == null) {
            throw new IllegalArgumentException("Customer must be set, it's null");
        }
    }

}

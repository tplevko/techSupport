package cz.muni.fi.tplevko.techsupport.dao.impl;

import cz.muni.fi.tplevko.techsupport.dao.CustomerDao;
import cz.muni.fi.tplevko.techsupport.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tplevko
 */
@Repository(value = "customerDao")
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer to be created is null");
        }
        if (customer.getId() != null) {
            throw new IllegalArgumentException("Customer to be created has already assigned id");
        }

        validateCustomer(customer);
        em.persist(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer to be updated is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("Customer to be updated has null id");
        }
        if (findCustomerById(customer.getId()) == null) {
            throw new IllegalArgumentException("Customer to be updated doesn't exist in DB");
        }

        validateCustomer(customer);

        em.merge(customer);

    }

    @Override
    public void deleteCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("customer to be removed is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("customer to be removed has not assigned id");
        }

        validateCustomer(customer);
        em.remove(customer);
    }

    @Override
    public Customer findCustomerById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Customer id to be retrieved is null");
        }

        Customer result = null;
        result = em.find(Customer.class, id);
        return result;
    }

    @Override
    public Customer findCustomerByEmail(String email) {

        Customer customer = null;
        final String qstring = "SELECT e FROM Customer e WHERE e.email = :email";

        TypedQuery<Customer> query = em.createQuery(qstring, Customer.class);
        query.setParameter("email", email);
        customer = query.getSingleResult();
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customers = new ArrayList<>();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Customer.class));
        Query q = em.createQuery(cq);
        customers = q.getResultList();
        return customers;
    }

    // TODO : revise.. 
    private void validateCustomer(Customer customer) {
        if (customer.getLastName() == null) {
            throw new IllegalArgumentException("Customer first name must be set, it's null");
        }
        if (customer.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Customer first name must be set, it's empty");
        }
        if (customer.getFirstName() == null) {
            throw new IllegalArgumentException("Customer last name must be set, it's null");
        }
        if (customer.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("Customer last name must be set, it's empty");
        }
        if (customer.getPassword() == null) {
            throw new IllegalArgumentException("Customer password must be set, it's null");
        }
        if (customer.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Customer address must be set, it's null");
        }
        if (customer.getEmail() == null) {
            throw new IllegalArgumentException("Customer email must be set, it's null");
        }
    }
}

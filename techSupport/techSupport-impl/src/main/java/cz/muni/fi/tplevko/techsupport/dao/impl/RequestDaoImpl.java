package cz.muni.fi.tplevko.techsupport.dao.impl;

import cz.muni.fi.tplevko.techsupport.dao.RequestDao;
import cz.muni.fi.tplevko.techsupport.entity.Customer;
import cz.muni.fi.tplevko.techsupport.entity.Employee;
import cz.muni.fi.tplevko.techsupport.entity.Request;
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
@Repository(value = "requestDao")
public class RequestDaoImpl implements RequestDao {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    @Override
    public void createRequest(Request request) {

        if (request == null) {
            throw new IllegalArgumentException("request to be created is null");
        }
        if (request.getId() != null) {
            throw new IllegalArgumentException("request to be created has already assigned id");
        }

        validateRequest(request);
        em.persist(request);
    }

    @Override
    public void updateRequest(Request request) {

        if (request == null) {
            throw new IllegalArgumentException("request to be updated is null");
        }
        if (request.getId() == null) {
            throw new IllegalArgumentException("request to be updated has null id");
        }
        if (findRequestById(request.getId()) == null) {
            throw new IllegalArgumentException("request to be updated doesn't exist in DB");
        }

        validateRequest(request);

        em.merge(request);
    }

    @Override
    public void deleteRequest(Request request) {

        if (request == null) {
            throw new IllegalArgumentException("request to be removed is null");
        }
        if (request.getId() == null) {
            throw new IllegalArgumentException("request to be removed has not assigned id");
        }

        validateRequest(request);
        em.remove(request);
    }

    @Override
    public Request findRequestById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Customer id to be retrieved is null");
        }

        Request result = null;
        result = em.find(Request.class, id);
        return result;
    }

    @Override
    public List<Request> findRequestByCustomer(Customer owner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Request> findRequestByAssignee(Employee assignee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Request> getAllRequests() {

        List<Request> requests = new ArrayList<>();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Request.class));
        Query q = em.createQuery(cq);
        requests = q.getResultList();
        return requests;
    }

    // TODO : revise.. 
    private void validateRequest(Request request) {
        if (request.getText() == null) {
            throw new IllegalArgumentException("Request text must be set, it's null");
        }
        if (request.getText().isEmpty()) {
            throw new IllegalArgumentException("Request text must be set, it's empty");
        }
    }

}

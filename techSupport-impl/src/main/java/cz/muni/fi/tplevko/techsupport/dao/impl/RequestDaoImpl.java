package cz.muni.fi.tplevko.techsupport.dao.impl;

import cz.muni.fi.tplevko.techsupport.dao.RequestDao;
import cz.muni.fi.tplevko.techsupport.entity.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tplevko
 */
@Repository(value = "requestDao")
public class RequestDaoImpl implements RequestDao {

    private static final Logger log = Logger.getLogger(RequestDaoImpl.class.getName());

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

        em.remove(em.contains(request) ? request : em.merge(request));
    }

    @Override
    public Request findRequestById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Request id to be retrieved is null");
        }

        Request result = null;
        result = em.find(Request.class, id);
        return result;
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

    @Override
    public List<Request> getActiveRequests() {

        List<Request> requests = new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery query = cb.createQuery(Request.class);
        Root<Request> c = query.from(Request.class);

        boolean myCondition = false;

        Predicate predicate = cb.equal(c.get("executed"), myCondition);
        query.where(predicate);
        Query q = em.createQuery(query);
        requests = q.getResultList();

        return requests;
    }

    /**
     * validates the request
     *
     * @param request
     */
    private void validateRequest(Request request) {
        if (request.getText() == null) {
            throw new IllegalArgumentException("Request text must be set, it's null");
        }
        if (request.getText().isEmpty()) {
            throw new IllegalArgumentException("Request text must be set, it's empty");
        }
        if (request.getRequestTitle() == null) {
            throw new IllegalArgumentException("Request title must be set, it's null");
        }
        if (request.getRequestTitle().isEmpty()) {
            throw new IllegalArgumentException("Request title must be set, it's empty");
        }
    }
}

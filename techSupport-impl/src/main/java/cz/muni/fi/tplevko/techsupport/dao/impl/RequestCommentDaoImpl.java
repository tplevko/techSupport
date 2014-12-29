package cz.muni.fi.tplevko.techsupport.dao.impl;

import cz.muni.fi.tplevko.techsupport.dao.RequestCommentDao;
import cz.muni.fi.tplevko.techsupport.entity.RequestComment;
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
@Repository(value = "requestCommentDao")
public class RequestCommentDaoImpl implements RequestCommentDao {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    @Override
    public void createRequestComment(RequestComment requestComment) {

        if (requestComment == null) {
            throw new IllegalArgumentException("Request to be created is null");
        }
        if (requestComment.getId() != null) {
            throw new IllegalArgumentException("Request to be created has already assigned id");
        }

        validateRequestComment(requestComment);
        em.persist(requestComment);
    }

    @Override
    public void updateRequestComment(RequestComment requestComment) {

        if (requestComment == null) {
            throw new IllegalArgumentException("requestComment to be updated is null");
        }
        if (requestComment.getId() == null) {
            throw new IllegalArgumentException("requestComment to be updated has null id");
        }
        if (findRequestCommentById(requestComment.getId()) == null) {
            throw new IllegalArgumentException("requestComment to be updated doesn't exist in DB");
        }

        validateRequestComment(requestComment);
        em.merge(requestComment);
    }

    @Override
    public void deleteRequestComment(RequestComment requestComment) {

        if (requestComment == null) {
            throw new IllegalArgumentException("requestComment to be removed is null");
        }
        if (requestComment.getId() == null) {
            throw new IllegalArgumentException("requestComment to be removed has not assigned id");
        }

        validateRequestComment(requestComment);
        em.remove(em.contains(requestComment) ? requestComment : em.merge(requestComment));
    }

    @Override
    public RequestComment findRequestCommentById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Product id to be retrieved is null");
        }

        RequestComment result = null;
        result = em.find(RequestComment.class, id);
        return result;
    }

    @Override
    public List<RequestComment> getAllRequestComments() {

        List<RequestComment> products = new ArrayList<>();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(RequestComment.class));
        Query q = em.createQuery(cq);
        products = q.getResultList();
        return products;
    }

    /**
     * validates request comments
     *
     * @param request
     */
    private void validateRequestComment(RequestComment request) {
        if (request.getText() == null) {
            throw new IllegalArgumentException("RequestComment text must be set, it's null");
        }
        if (request.getText().isEmpty()) {
            throw new IllegalArgumentException("RequestComment text must be set, it's empty");
        }
        if (request.getCommenter() == null) {
            throw new IllegalArgumentException("RequestComment commenter must be set, it's null");
        }
    }

}

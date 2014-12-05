package cz.muni.fi.tplevko.techsupport.dao.impl;

import cz.muni.fi.tplevko.techsupport.dao.PasswordChangeDao;
import cz.muni.fi.tplevko.techsupport.entity.PasswordChange;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tplevko
 */
@Repository(value = "passwordChangeDao")
public class PasswordChangeDaoImpl implements PasswordChangeDao {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    @Override
    public String createPasswordChange(PasswordChange passwordChange) {

        if (passwordChange == null) {
            throw new IllegalArgumentException("PasswordChange to be created is null");
        }
        if (passwordChange.getId() != null) {
            throw new IllegalArgumentException("PasswordChange to be created has already assigned id");
        }

        validatePasswordChange(passwordChange);
        em.persist(passwordChange);
        return passwordChange.getId();
    }

    @Override
    public void deletePasswordChange(PasswordChange passwordChange) {

        if (passwordChange == null) {
            throw new IllegalArgumentException("PasswordChange to be removed is null");
        }
        if (passwordChange.getId() == null) {
            throw new IllegalArgumentException("PasswordChange to be removed has not assigned id");
        }

        validatePasswordChange(passwordChange);

        em.remove(em.contains(passwordChange) ? passwordChange : em.merge(passwordChange));
    }

    @Override
    public PasswordChange findPasswordChangeId(String id) {

        if (id == null) {
            throw new IllegalArgumentException("PasswordChange id to be retrieved is null");
        }

        PasswordChange result = null;
        result = em.find(PasswordChange.class, id);
        return result;
    }

    private void validatePasswordChange(PasswordChange passwordChange) {

        if (passwordChange.getRequester() == null) {
            throw new IllegalArgumentException("Requester must be set, it's null");
        }
    }

}

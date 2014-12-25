package cz.muni.fi.tplevko.techsupport.dao.impl;

import cz.muni.fi.tplevko.techsupport.dao.AccountDao;
import cz.muni.fi.tplevko.techsupport.entity.Account;
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
@Repository(value = "accountDao")
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }
        if (account.getId() != null) {
            throw new IllegalArgumentException("Account to be created has already assigned id");
        }

        validateAccount(account);
        em.persist(account);
    }

    @Override
    public void updateAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("account to be updated is null");
        }
        if (account.getId() == null) {
            throw new IllegalArgumentException("account to be updated has null id");
        }
        if (findAccountById(account.getId()) == null) {
            throw new IllegalArgumentException("account to be updated doesn't exist in DB");
        }

        validateAccount(account);

        em.merge(account);
    }

    @Override
    public void deleteAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("account to be removed is null");
        }
        if (account.getId() == null) {
            throw new IllegalArgumentException("account to be removed has not assigned id");
        }

        validateAccount(account);
        em.remove(em.contains(account) ? account : em.merge(account));
    }

    @Override
    public Account findAccountById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Account id to be retrieved is null");
        }

        Account result = null;
        result = em.find(Account.class, id);
        return result;
    }

    @Override
    public Account findAccountByEmail(String email) {

        Account account = null;
        final String qstring = "SELECT e FROM Account e WHERE e.email = :email";

        try {
            TypedQuery<Account> query = em.createQuery(qstring, Account.class);
            query.setParameter("email", email);
            account = query.getSingleResult();
            return account;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Account.class));
        Query q = em.createQuery(cq);
        accounts = q.getResultList();
        return accounts;
    }

    // TODO : revise.. 
    private void validateAccount(Account account) {
        if (account.getLastName() == null) {
            throw new IllegalArgumentException("account first name must be set, it's null");
        }
        if (account.getLastName().isEmpty()) {
            throw new IllegalArgumentException("account first name must be set, it's empty");
        }
        if (account.getFirstName() == null) {
            throw new IllegalArgumentException("account last name must be set, it's null");
        }
        if (account.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("account last name must be set, it's empty");
        }
        if (account.getFirstName() == null) {
            throw new IllegalArgumentException("account password must be set, it's null");
        }
        if (account.getEmail().isEmpty()) {
            throw new IllegalArgumentException("account address must be set, it's null");
        }
        if (account.getEmail() == null) {
            throw new IllegalArgumentException("account email must be set, it's null");
        }
    }
}

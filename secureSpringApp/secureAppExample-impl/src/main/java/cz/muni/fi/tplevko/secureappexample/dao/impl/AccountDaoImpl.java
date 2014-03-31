package cz.muni.fi.tplevko.secureappexample.dao.impl;

import cz.muni.fi.tplevko.secureappexample.dao.AccountDao;
import cz.muni.fi.tplevko.secureappexample.entity.Account;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.InternetAddress;
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
@Repository(value = "accountDAO")
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
    public void deleteAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }
        if (account.getId() == null) {
            throw new IllegalArgumentException("Account to be deleted has not assigned id");
        }

        validateAccount(account);
        em.remove(account);
    }

    @Override
    public void updateAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("Account to be updated is null");
        }
        if (account.getId() == null) {
            throw new IllegalArgumentException("Account to be updated has null id");
        }
        if (findAccountById(account.getId()) == null) {
            throw new IllegalArgumentException("Account to be updated doesn't exist in DB");
        }

        validateAccount(account);
        em.merge(account);

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
    public Account findAccountByName(String name) {

        Account account = null;
        final String qstring = "SELECT e FROM Account e WHERE e.name = :name";

        TypedQuery<Account> query = em.createQuery(qstring, Account.class);
        query.setParameter("name", name);
        account = query.getSingleResult();
        return account;
    }

    @Override
    public Account findAccountByEmail(String email) {

        Account account = null;
        final String qstring = "SELECT e FROM Account e WHERE e.email = :email";

        TypedQuery<Account> query = em.createQuery(qstring, Account.class);
        query.setParameter("email", email);
        account = query.getSingleResult();
        return account;
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

    private void validateAccount(Account account) {
        if (account.getName() == null) {
            throw new IllegalArgumentException("Account name must be set, it's null");
        }
        if (account.getName().isEmpty()) {
            throw new IllegalArgumentException("Account name must be set, it's empty");
        }
        if (account.getPassword() == null) {
            throw new IllegalArgumentException("Account password must be set, it's null");
        }
        if (account.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Account address must be set, it's null");
        }
        if (account.getEmail() == null) {
            throw new IllegalArgumentException("Account email must be set, it's null");
        }
//        if (account.getCustomer() == null) {
//            throw new IllegalArgumentException("Customer must be set, it's null");
//        }
    }
}

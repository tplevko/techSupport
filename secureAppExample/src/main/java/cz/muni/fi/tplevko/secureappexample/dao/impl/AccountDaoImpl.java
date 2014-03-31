package cz.muni.fi.tplevko.secureappexample.dao.impl;

import cz.muni.fi.tplevko.secureappexample.dao.AccountDao;
import cz.muni.fi.tplevko.secureappexample.entity.Account;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tplevko
 */
@Repository(value = "accountDAO")
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext(unitName = "TestInMemoryPU")
    private EntityManager em;
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public Account createAccount(Account account) {
       
        if (account == null) {
            throw new IllegalArgumentException("Account to be created is null");
        }
        if (account.getId() != null) {
            throw new IllegalArgumentException("Account to be created has already assigned id");
        }

        validateAccount(account);
        em.persist(account);
        
        return account;
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
    public Account updateAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account to be updated is null");
        }
        if (account.getId() == null) {
            throw new IllegalArgumentException("Account to be updated has null id");
        }
        if (findAccountAccrodToId(account.getId()) == null) {
            throw new IllegalArgumentException("Account to be updated doesn't exist in DB");
        }
        
        validateAccount(account);
        return em.merge(account);
    }

    @Override
    public Account findAccountAccrodToId(Long id) {
  
        if (id == null) {
            throw new IllegalArgumentException("Account id to be retrieved is null");
        }
        
        Account result = null;
        result = em.find(Account.class, id);
        return result;
    }

    @Override
    public Account findAccountAccordToName(String name) {
      
        Account account = null;
        final String qstring = "SELECT e FROM Account e WHERE e.name = :name";
        
        TypedQuery<Account> query = em.createQuery(qstring, Account.class);
        query.setParameter("name", name);
        account = query.getSingleResult();
        return account;
    }
    
    @Override
    public Account findAccountAccordToEmail(InternetAddress email) {
      
        Account account = null;
        final String qstring = "SELECT e FROM Account e WHERE e.email = :email";
        
        TypedQuery<Account> query = em.createQuery(qstring, Account.class);
        query.setParameter("email", email);
        account = query.getSingleResult();
        return account;
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

package cz.muni.fi.tplevko.techsupport.dao.impl;

import cz.muni.fi.tplevko.techsupport.dao.AccountDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.shiro.authc.Account;
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
    public void createAccount(cz.muni.fi.tplevko.techsupport.entity.Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAccount(cz.muni.fi.tplevko.techsupport.entity.Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAccount(cz.muni.fi.tplevko.techsupport.entity.Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public cz.muni.fi.tplevko.techsupport.entity.Account findAccountById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public cz.muni.fi.tplevko.techsupport.entity.Account findAccountByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<cz.muni.fi.tplevko.techsupport.entity.Account> getAllAccounts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

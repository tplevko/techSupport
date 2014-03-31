package cz.muni.fi.tplevko.secureappexample.dao.impl;

import cz.muni.fi.tplevko.secureappexample.dao.ItemDao;
import cz.muni.fi.tplevko.secureappexample.entity.Item;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tplevko
 */
@Repository(value = "itemDao")
public class ItemDaoImpl implements ItemDao {
    
    @PersistenceContext(unitName = "TestInMemoryPU")
    private EntityManager em;

    @Override
    public Item createItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item updateItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item findItemAccrodToId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item findItemAccordToName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

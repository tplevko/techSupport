package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.dao.ItemDao;
import cz.muni.fi.tplevko.secureappexample.entity.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author tplevko
 */
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;
    
    @Override
    public void createItem(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("Item to be created is null");
        }

        if (item.getId() != null) {
            throw new IllegalArgumentException("ItemDTO has set id");
        }

        itemDao.createItem(item);
    }

    @Override
    public void updateItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item findItem(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item findItemByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}

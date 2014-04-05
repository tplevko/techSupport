package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.dao.ItemDao;
import cz.muni.fi.tplevko.secureappexample.entity.Item;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tplevko
 */
@Service(value = "itemServiceImpl")
@Transactional
public class ItemServiceImpl implements ItemService {

    private static final Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

    // TODO : vymysliet ako to logovanie... 
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

        if (item.getId() == null) {
            throw new IllegalArgumentException("ItemDTO has not set ID");
        }

        itemDao.updateItem(item);
    }

    @Override
    public void deleteItem(Item item) {

        if (item.getId() == null) {
            throw new IllegalArgumentException("ItemDTO has not set ID");
        }

        itemDao.deleteItem(item);
    }

    @Override
    public Item findItem(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ItemDTO has not set ID");
        }

        return itemDao.findItemById(id);
    }

    @Override
    public Item findItemByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("ItemDTO has not set ID");
        }

        return itemDao.findItemByName(name);
    }

    @Override
    public List<Item> getAllItems() {

        return itemDao.getAllItems();
    }

}

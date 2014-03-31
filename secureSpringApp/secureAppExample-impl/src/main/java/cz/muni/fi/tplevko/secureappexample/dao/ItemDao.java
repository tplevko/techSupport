package cz.muni.fi.tplevko.secureappexample.dao;

import cz.muni.fi.tplevko.secureappexample.entity.Item;

/**
 *
 * @author tplevko
 */
public interface ItemDao {

    public void createItem(Item item);

    public void updateItem(Item item);

    public void deleteItem(Item item);

    public Item findItemById(Long id);

    public Item findItemByName(String name);
}

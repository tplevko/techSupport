package cz.muni.fi.tplevko.secureappexample.dao;

import cz.muni.fi.tplevko.secureappexample.entity.Item;

/**
 *
 * @author tplevko
 */
public interface ItemDao {

    public Item createItem(Item item);
    
    public void deleteItem(Item item);
    
    public Item updateItem(Item item);
    
    public Item findItemAccrodToId(Long id);
    
    public Item findItemAccordToName(String name);
}

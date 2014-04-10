package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.entity.dto.ItemDto;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface ItemService {
    
    public void createItem(ItemDto item);

    public void updateItem(ItemDto item);

    public void deleteItem(ItemDto item);

    public ItemDto findItem(Long id);

    public ItemDto findItemByName(String name);

    public List<ItemDto> getAllItems();
}

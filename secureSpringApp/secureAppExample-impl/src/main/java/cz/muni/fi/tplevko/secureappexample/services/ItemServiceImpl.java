package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.dao.ItemDao;
import cz.muni.fi.tplevko.secureappexample.entity.Item;
import cz.muni.fi.tplevko.secureappexample.entity.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.dozer.DozerBeanMapper;
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
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public void createItem(ItemDto itemDto) {

        if (itemDto == null) {
            throw new IllegalArgumentException("Item to be created is null");
        }

        if (itemDto.getId() != null) {
            throw new IllegalArgumentException("ItemDTO has set id");
        }
        Item item = mapper.map(itemDto, Item.class);

        itemDao.createItem(item);
    }

    @Override
    @Transactional
    public void updateItem(ItemDto itemDto) {

        if (itemDto.getId() == null) {
            throw new IllegalArgumentException("ItemDTO has not set ID");
        }
        Item item = mapper.map(itemDto, Item.class);

        itemDao.updateItem(item);
    }

    @Override
    @Transactional
    public void deleteItem(ItemDto itemDto) {

        if (itemDto.getId() == null) {
            throw new IllegalArgumentException("ItemDTO has not set ID");
        }
        Item item = mapper.map(itemDto, Item.class);
        itemDao.deleteItem(item);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDto findItem(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ItemDTO has not set ID");
        }

        Item item = itemDao.findItemById(id);
        return mapper.map(item, ItemDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDto findItemByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("ItemDTO has not set ID");
        }
        Item item = itemDao.findItemByName(name);
        return mapper.map(item, ItemDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemDto> getAllItems() {
        
        
                List<Item> items = itemDao.getAllItems();

        List<ItemDto> itemsReturn = new ArrayList<>();
        for (Item i : items) {
            itemsReturn.add(mapper.map(i, ItemDto.class));
        }
        return itemsReturn;
    }

}

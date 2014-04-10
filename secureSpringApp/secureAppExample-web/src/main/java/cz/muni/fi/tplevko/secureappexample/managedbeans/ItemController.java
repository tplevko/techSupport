package cz.muni.fi.tplevko.secureappexample.managedbeans;

import cz.muni.fi.tplevko.secureappexample.entity.Item;
import cz.muni.fi.tplevko.secureappexample.services.ItemService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component
@ManagedBean
@SessionScoped
public class ItemController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired(required = true)
//    @Qualifier("itemService")
    private ItemService itemService;
    
    
    private Item selectedItem;

    public String name;
    public BigDecimal price;

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //get all accounts data from database
    public List<Item> getItemList() {
        return itemService.getAllItems();
    }

    //add a new account data into database
    public void addItem() {

        try {

            Item item = new Item();
            item.setName(name);
            item.setPrice(price);
            itemService.createItem(item);

        } catch (Exception e) {

            // TODO : sprava o neuspechu
        }
    }
    
    public String selectItem(Long Id) {
//        
//        return itemService.findItem(ID).getId();

        selectedItem = itemService.findItem(Id);
        return "/item/viewItem?faces-redirect=true";
    }
    
    public String deleteItem(Long Id) {
        
        Item itemToBeDeleted = itemService.findItem(Id);
        itemService.deleteItem(itemToBeDeleted);
        return "/item/itemList?faces-redirect=true";
    }
    
    public String editItem(Long Id) {
        
        return null;
    }
        
//        return itemService.findItem(Id);
//        
//    }

}

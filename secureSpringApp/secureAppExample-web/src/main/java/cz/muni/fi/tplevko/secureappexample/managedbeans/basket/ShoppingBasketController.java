package cz.muni.fi.tplevko.secureappexample.managedbeans.basket;

import cz.muni.fi.tplevko.secureappexample.entity.Item;
import cz.muni.fi.tplevko.secureappexample.entity.Order;
import cz.muni.fi.tplevko.secureappexample.entity.dto.ItemDto;
import cz.muni.fi.tplevko.secureappexample.entity.dto.OrderDto;
import cz.muni.fi.tplevko.secureappexample.services.ItemService;
import cz.muni.fi.tplevko.secureappexample.services.OrderService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component
@Scope("session")
public class ShoppingBasketController {

    private List<ItemDto> itemList = new ArrayList<ItemDto>();

    @Autowired
    private ItemService itemService;
    
    @Autowired
    private OrderService orderService;
    
    public String addItemIntoBasket(Long id) {
        
        ItemDto item = itemService.findItem(id);
        
        itemList.add(item);
        
        return null;
    }
    
    public String removeItemFromBasket(Long id) {
           
        ItemDto item = itemService.findItem(id);
        itemList.remove(item);
        
        return null;
    }

    public String placeOrder() {

        OrderDto order = new OrderDto();
        order.setItems(itemList);
        order.setTotalPrice(getTotalPrice());
        
        
        // TODO : ako ziskam aktualne prihlaseneho usera?
        order.setOwner(null);

        return null;

    }

    private BigDecimal getTotalPrice() {

        BigDecimal total = BigDecimal.ZERO;

        for (ItemDto item : itemList) {

            total = total.add(item.getPrice());
        }

        return total;
    }
    
    
}

package cz.muni.fi.tplevko.secureappexample.managedbeans.basket;

import cz.muni.fi.tplevko.secureappexample.entity.Item;
import cz.muni.fi.tplevko.secureappexample.entity.Order;
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

    private List<Item> itemList = new ArrayList<Item>();

    @Autowired
    private OrderService orderService;

    public String placeOrder() {

        Order order = new Order();
        order.setItems(itemList);
        order.setTotalPrice(getTotalPrice());
        
        
        // TODO : ako ziskam aktualne prihlaseneho usera?
        order.setOwner(null);

        return null;

    }

    private BigDecimal getTotalPrice() {

        BigDecimal total = BigDecimal.ZERO;

        for (Item item : itemList) {

            total = total.add(item.getPrice());
        }

        return total;
    }
}

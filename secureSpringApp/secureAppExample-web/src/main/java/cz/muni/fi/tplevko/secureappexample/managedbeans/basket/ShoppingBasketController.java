package cz.muni.fi.tplevko.secureappexample.managedbeans.basket;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.entity.Item;
import cz.muni.fi.tplevko.secureappexample.entity.Invoice;
import cz.muni.fi.tplevko.secureappexample.entity.dto.ItemDto;
import cz.muni.fi.tplevko.secureappexample.entity.dto.InvoiceDto;
import cz.muni.fi.tplevko.secureappexample.services.AccountService;
import cz.muni.fi.tplevko.secureappexample.services.ItemService;
import cz.muni.fi.tplevko.secureappexample.services.InvoiceService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component
@ManagedBean
@Scope("session")
public class ShoppingBasketController {

    private Map<Long, ItemDto> itemsMap = new HashMap<Long, ItemDto>();

    @Autowired
    private ItemService itemService;

    @Autowired
    private InvoiceService orderService;

    @Autowired
    private AccountService accService;

    public String addItemIntoBasket(Long id) {

        ItemDto item = itemService.findItem(id);

        itemsMap.put(id, item);

        return null;
    }

    public String removeItemFromBasket(Long id) {

        itemsMap.remove(id);

        return "/basket/showBasket?faces-redirect=true";
    }

    public String placeOrder() {

        InvoiceDto order = new InvoiceDto();
        List<ItemDto> items = new ArrayList<ItemDto>(itemsMap.values());
        order.setItems(items);
        order.setTotalPrice(getTotalPrice());

//        SecurityUtils.getSubject().
        // TODO : ako ziskam aktualne prihlaseneho usera?
        order.setOwner(accService.findAccount(1l));
        
        orderService.createOrder(order);
        
        return "/order/orderList?faces-redirect=true";
    }

    public List<ItemDto> getBasket() {

        return new ArrayList<ItemDto>(itemsMap.values());

    }

    private BigDecimal getTotalPrice() {

        BigDecimal total = BigDecimal.ZERO;
        List<ItemDto> items = new ArrayList<ItemDto>(itemsMap.values());

        for (ItemDto item : items) {

            total = total.add(item.getPrice());
        }

        return total;
    }

}

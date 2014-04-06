package cz.muni.fi.tplevko.secureappexample.managedbeans.basket;

import cz.muni.fi.tplevko.secureappexample.entity.Item;
import java.util.ArrayList;
import java.util.List;
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
    
    // TODO 
}

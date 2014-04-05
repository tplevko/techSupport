package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.entity.Order;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface OrderService {

    
    public void createOrder(Order order);

    public void updateOrder(Order order);

    public void deleteOrder(Order order);

    public Order findOrder(Long id);

    public Order findOrderByOwner(Account account);

    public List<Order> getAllOrders();
}

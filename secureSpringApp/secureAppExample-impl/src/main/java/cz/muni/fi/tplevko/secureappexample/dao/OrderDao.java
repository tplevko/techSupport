package cz.muni.fi.tplevko.secureappexample.dao;

import cz.muni.fi.tplevko.secureappexample.entity.Order;

/**
 *
 * @author tplevko
 */
public interface OrderDao {

    public void createOrder(Order order);

    public void deleteOrder(Order order);

    public void updateOrder(Order order);

    public Order findOrderById(Long id);

    
}

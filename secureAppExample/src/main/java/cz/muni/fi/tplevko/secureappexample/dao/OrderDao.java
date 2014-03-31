package cz.muni.fi.tplevko.secureappexample.dao;

import cz.muni.fi.tplevko.secureappexample.entity.Order;

/**
 *
 * @author tplevko
 */
public interface OrderDao {

    public Order createOrder(Order order);

    public void deleteOrder(Order order);

    public Order updateOrder(Order order);

    public Order findOrderAccrodToId(Long id);

}

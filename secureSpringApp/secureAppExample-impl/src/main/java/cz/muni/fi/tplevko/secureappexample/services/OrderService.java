package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.entity.Order;
import cz.muni.fi.tplevko.secureappexample.entity.dto.AccountDto;
import cz.muni.fi.tplevko.secureappexample.entity.dto.OrderDto;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface OrderService {

    
    public void createOrder(OrderDto order);

    public void updateOrder(OrderDto order);

    public void deleteOrder(OrderDto order);

    public OrderDto findOrder(Long id);

    public OrderDto findOrderByOwner(AccountDto account);

    public List<OrderDto> getAllOrders();
}

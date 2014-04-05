package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.dao.OrderDao;
import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.entity.Order;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tplevko
 */
@Service(value = "orderServiceImpl")
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

    // TODO : na to logovanie sa tam pozri, ako to maju chalani spravene pri tom creature huntingu...
    @Autowired
    private OrderDao orderDao;

    @Override
    public void createOrder(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("order to be created is null");
        }

        if (order.getId() != null) {
            throw new IllegalArgumentException("orderdto has set id");
        }

        orderDao.createOrder(order);
    }

    @Override
    public void updateOrder(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("order to be created is null");
        }

        orderDao.updateOrder(order);
    }

    @Override
    public void deleteOrder(Order order) {
        
        if (order == null) {
            throw new IllegalArgumentException("order to be created is null");
        }

        if (order.getId() == null) {
            throw new IllegalArgumentException("order has not set the ID");
        }

        orderDao.deleteOrder(order);
    }

    @Override
    public Order findOrder(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        Order order = orderDao.findOrderById(id);
        return order;

    }

    @Override
    public Order findOrderByOwner(Account account) {

        if (account == null) {
            throw new IllegalArgumentException("order owner not specified");
        }

        return null;
    }

    @Override
    public List<Order> getAllOrders() {

        return orderDao.getAllOrders();
    }

}

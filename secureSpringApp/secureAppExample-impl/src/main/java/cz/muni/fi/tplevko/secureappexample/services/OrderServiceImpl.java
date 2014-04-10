package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.dao.OrderDao;
import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.entity.Order;
import cz.muni.fi.tplevko.secureappexample.entity.dto.AccountDto;
import cz.muni.fi.tplevko.secureappexample.entity.dto.OrderDto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.dozer.DozerBeanMapper;
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

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public void createOrder(OrderDto orderDto) {

        if (orderDto == null) {
            throw new IllegalArgumentException("order to be created is null");
        }

        if (orderDto.getId() != null) {
            throw new IllegalArgumentException("orderdto has set id");
        }

        Order order = mapper.map(orderDto, Order.class);

        orderDao.createOrder(order);
    }

    @Override
    @Transactional
    public void updateOrder(OrderDto orderDto) {

        if (orderDto == null) {
            throw new IllegalArgumentException("order to be created is null");
        }
        Order order = mapper.map(orderDto, Order.class);

        orderDao.updateOrder(order);
    }

    @Override
    @Transactional
    public void deleteOrder(OrderDto orderDto) {

        if (orderDto == null) {
            throw new IllegalArgumentException("order to be created is null");
        }

        if (orderDto.getId() == null) {
            throw new IllegalArgumentException("order has not set the ID");
        }

        Order order = mapper.map(orderDto, Order.class);

        orderDao.deleteOrder(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto findOrder(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        Order order = orderDao.findOrderById(id);

        return mapper.map(order, OrderDto.class);

    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto findOrderByOwner(AccountDto accountDto) {

        if (accountDto == null) {
            throw new IllegalArgumentException("order owner not specified");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {

        List<Order> orders = orderDao.getAllOrders();

        List<OrderDto> accountsReturn = new ArrayList<OrderDto>();
        for (Order o : orders) {
            accountsReturn.add(mapper.map(o, OrderDto.class));
        }
        return accountsReturn;
    }

}

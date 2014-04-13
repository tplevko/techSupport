package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.dao.InvoiceDao;
import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.entity.Invoice;
import cz.muni.fi.tplevko.secureappexample.entity.dto.AccountDto;
import cz.muni.fi.tplevko.secureappexample.entity.dto.InvoiceDto;
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
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

    // TODO : na to logovanie sa tam pozri, ako to maju chalani spravene pri tom creature huntingu...
    @Autowired
    private InvoiceDao orderDao;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    @Transactional
    public void createOrder(InvoiceDto orderDto) {

        if (orderDto == null) {
            throw new IllegalArgumentException("order to be created is null");
        }

        if (orderDto.getId() != null) {
            throw new IllegalArgumentException("orderdto has set id");
        }

        Invoice order = mapper.map(orderDto, Invoice.class);

        orderDao.createOrder(order);
    }

    @Override
    @Transactional
    public void updateOrder(InvoiceDto orderDto) {

        if (orderDto == null) {
            throw new IllegalArgumentException("order to be created is null");
        }
        Invoice order = mapper.map(orderDto, Invoice.class);

        orderDao.updateOrder(order);
    }

    @Override
    @Transactional
    public void deleteOrder(InvoiceDto orderDto) {

        if (orderDto == null) {
            throw new IllegalArgumentException("order to be created is null");
        }

        if (orderDto.getId() == null) {
            throw new IllegalArgumentException("order has not set the ID");
        }

        Invoice order = mapper.map(orderDto, Invoice.class);

        orderDao.deleteOrder(order);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceDto findOrder(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID to retrieve can't be null");
        }
        Invoice order = orderDao.findOrderById(id);

        return mapper.map(order, InvoiceDto.class);

    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceDto findOrderByOwner(AccountDto accountDto) {

        if (accountDto == null) {
            throw new IllegalArgumentException("order owner not specified");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceDto> getAllOrders() {

        List<Invoice> orders = orderDao.getAllOrders();

        List<InvoiceDto> accountsReturn = new ArrayList<InvoiceDto>();
        for (Invoice o : orders) {
            accountsReturn.add(mapper.map(o, InvoiceDto.class));
        }
        return accountsReturn;
    }

}

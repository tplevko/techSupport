package cz.muni.fi.tplevko.secureappexample.dao;

import cz.muni.fi.tplevko.secureappexample.entity.Invoice;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface InvoiceDao {

    public void createOrder(Invoice order);

    public void deleteOrder(Invoice order);

    public void updateOrder(Invoice order);

    public Invoice findOrderById(Long id);

    public List<Invoice> getAllOrders();
}

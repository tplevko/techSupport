package cz.muni.fi.tplevko.secureappexample.services;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.entity.Invoice;
import cz.muni.fi.tplevko.secureappexample.entity.dto.AccountDto;
import cz.muni.fi.tplevko.secureappexample.entity.dto.InvoiceDto;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface InvoiceService {

    
    public void createOrder(InvoiceDto order);

    public void updateOrder(InvoiceDto order);

    public void deleteOrder(InvoiceDto order);

    public InvoiceDto findOrder(Long id);

    public InvoiceDto findOrderByOwner(AccountDto account);

    public List<InvoiceDto> getAllOrders();
}

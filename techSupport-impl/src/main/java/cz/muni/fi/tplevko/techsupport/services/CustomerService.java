package cz.muni.fi.tplevko.techsupport.services;

import cz.muni.fi.tplevko.techsupport.entity.Customer;
import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import java.util.List;

/**
 *
 * @author tplevko
 */
public interface CustomerService {

    public void createCustomer(CustomerDto customer);

    public void updateCustomer(CustomerDto customer);

    public void deleteCustomer(CustomerDto customer);

    public CustomerDto findCustomerById(Long id);

    public CustomerDto findCustomerByEmail(String email);

    public List<CustomerDto> getAllCustomers();

    public void activateCustomerAccount(CustomerDto customer);
}

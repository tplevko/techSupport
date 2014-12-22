package cz.muni.fi.tplevko.techsupport.dao;

import cz.muni.fi.tplevko.techsupport.entity.Customer;
import java.util.List;

/**
 * Interface for the basic customer DAO
 * 
 * @author tplevko
 */
public interface CustomerDao {

    public void createCustomer(Customer customer);

    public void updateCustomer(Customer customer);

    public void deleteCustomer(Customer customer);

    public Customer findCustomerById(Long id);

    public Customer findCustomerByEmail(String email);

    public List<Customer> getAllCustomers();
}

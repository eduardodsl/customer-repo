package br.com.dsleite.customerrepo.service;

import br.com.dsleite.customerrepo.exceptions.AddressNotFoundException;
import br.com.dsleite.customerrepo.exceptions.DataNotFoundException;
import br.com.dsleite.customerrepo.model.Customer;

public interface CustomerService {
    
    Iterable<Customer> findAll();
    Customer findById(Long id);
    void insert(Customer customer) throws AddressNotFoundException;
    Customer insert(String name, String surname, String cep) throws AddressNotFoundException;
    void update(Long id, Customer customer) throws AddressNotFoundException, DataNotFoundException;
    Customer update(Long id, String name, String surname, String cep) throws AddressNotFoundException, DataNotFoundException;
    void delete(Long id) throws DataNotFoundException;

}

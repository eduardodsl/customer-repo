package br.com.dsleite.customerrepo.service;

import br.com.dsleite.customerrepo.model.Customer;

public interface CustomerService {
    
    Iterable<Customer> findAll();
    Customer findById(Long id);
    void insert(Customer customer);
    Customer insert(String name, String surname, String cep);
    void update(Long id, Customer customer);
    Customer update(Long id, String name, String surname, String cep);
    void delete(Long id);

}

package br.com.dsleite.customerrepo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.dsleite.customerrepo.exceptions.AddressNotFoundException;
import br.com.dsleite.customerrepo.exceptions.DataNotFoundException;
import br.com.dsleite.customerrepo.model.Address;
import br.com.dsleite.customerrepo.model.AddressRepository;
import br.com.dsleite.customerrepo.model.Customer;
import br.com.dsleite.customerrepo.model.CustomerRepository;
import br.com.dsleite.customerrepo.service.CustomerService;
import br.com.dsleite.customerrepo.service.ViaCepService;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.get();
    }

    @Override
    public void insert(Customer customer) throws AddressNotFoundException {
        saveCustomerWithCEP(customer);
    }

    @Override
    public Customer insert(String name, String surname, String cep) throws AddressNotFoundException {
        Customer customer = new Customer();
        customer.setFullName(name, surname);
        Address address = new Address();
        address.setCep(cep);
        customer.setAddress(address);
        saveCustomerWithCEP(customer);
        return customer;
    }

    @Override
    public void update(Long id, Customer update) throws AddressNotFoundException, DataNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            saveCustomerWithCEP(update);
        }else{
            throw new DataNotFoundException("Customer id "+id+" not found");
        }
    }

    @Override
    public Customer update(Long id, String name, String surname, String cep) throws AddressNotFoundException {

        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            Customer update = new Customer();
            update.setFullName(name, surname);
            Customer current = customer.get();
            if(current.getAddress().getCep() != cep){
                Address address = new Address();
                address.setCep(cep);
                update.setAddress(address);
            }
            update.setId(id);
            saveCustomerWithCEP(update);
        }
        return customer.get();
    }

    @Override
    public void delete(Long id) throws DataNotFoundException {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new DataNotFoundException("Customer with id "+id+" not found");
        }
    }

    private void saveCustomerWithCEP(Customer customer) throws AddressNotFoundException {
        String cep = customer.getAddress().getCep();
        try {
            Address address = addressRepository.findById(cep).orElseGet(() -> {
                Address newAddress = viaCepService.findCEP(cep);
                addressRepository.save(newAddress);
                return newAddress;
            });
            customer.setAddress(address);
            customerRepository.save(customer);
        } catch (Exception e){
            throw new AddressNotFoundException("The CEP address ["+cep+"] is not valid");
        }
    }

}

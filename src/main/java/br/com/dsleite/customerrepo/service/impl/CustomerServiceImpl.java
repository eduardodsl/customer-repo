package br.com.dsleite.customerrepo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void insert(Customer customer){
        saveCustomerWithCEP(customer);
    }

    @Override
    public Customer insert(String name, String surname, String cep){
        Customer customer = new Customer();
        customer.setFullName(name, surname);
        Address address = new Address();
        address.setCep(cep);
        customer.setAddress(address);
        saveCustomerWithCEP(customer);
        return customer;
    }

    @Override
    public void update(Long id, Customer update){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            saveCustomerWithCEP(update);
        }
    }

    @Override
    public Customer update(Long id, String name, String surname, String cep){

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
    public void delete(Long id){
        customerRepository.deleteById(id);
    }

    private void saveCustomerWithCEP(Customer customer){
        String cep = customer.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = viaCepService.findCEP(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        customer.setAddress(address);
        customerRepository.save(customer);
    }

}

package br.com.dsleite.customerrepo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsleite.customerrepo.model.Customer;
import br.com.dsleite.customerrepo.service.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("customers")
public class CustomerRestController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Iterable<Customer>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.findById(id));
    }

    // I don't know why, but the application is failing to deserialize the json request body into a Customer object on POST and PUT, I will just work with plain form-data for now
    /*
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> insert(@RequestBody Customer customer){
        customerService.insert(customer);
        return ResponseEntity.ok(customer);
    }*/

    @PostMapping
    public ResponseEntity<Customer> insert(@RequestBody String name, @RequestBody String surname, @RequestBody String cep){
        Customer customer = customerService.insert(name, surname, cep);
        return ResponseEntity.ok(customer);
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer){
        customerService.update(id, customer);
        return ResponseEntity.ok(customer);
    }
    */

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody String name, @RequestBody String surname, @RequestBody String cep){
        Customer customer = customerService.update(id, name, surname, cep);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }

}

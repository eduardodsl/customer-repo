package br.com.dsleite.customerrepo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsleite.customerrepo.exceptions.AddressNotFoundException;
import br.com.dsleite.customerrepo.exceptions.DataNotFoundException;
import br.com.dsleite.customerrepo.model.Customer;
import br.com.dsleite.customerrepo.service.CustomerService;
import br.com.dsleite.customerrepo.utils.ResponseUtils;
import br.com.dsleite.customerrepo.utils.Validate;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("customers")
public class CustomerRestController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        Iterable<Customer> customers = customerService.findAll();
        return ResponseEntity.ok(ResponseUtils.successfullResponse("CUSTOMERS_FOUND", customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok(ResponseUtils.successfullResponse("CUSTOMERS_FOUND", customer));
    }

    // I don't know why, but the application is failing to deserialize the json request body into a Customer object on POST and PUT, I will just work with plain form-data for now
    /*
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> insert(@RequestBody Customer customer){
        customerService.insert(customer);
        return ResponseEntity.ok(customer);
    }*/

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody String name, @RequestBody String surname, @RequestBody String cep){

        Validate<String> validator = new Validate<>();
        validator.addField("name", name).addField("surname", surname).addField("cep", cep).validate();
        
        if(!validator.isValid()){
            return ResponseEntity.ok(ResponseUtils.failedResponse("CUSTOMER_NOT_INSERTED", validator.getInvalidFields()));
        }else{
            try {
                Customer customer = customerService.insert(name, surname, cep);
                return ResponseEntity.ok(ResponseUtils.successfullResponse("CUSTOMER_INSERTED", customer));
            }catch (AddressNotFoundException e){
                return ResponseEntity.ok(ResponseUtils.failedResponse("ADDRESS_NOT_FOUND", "cep"));
            }
        }

    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer){
        customerService.update(id, customer);
        return ResponseEntity.ok(customer);
    }
    */

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody String name, @RequestBody String surname, @RequestBody String cep){
        
        Validate<String> validator = new Validate<>();
        validator.addField("name", name).addField("surname", surname).addField("cep", cep).validate();
        
        if(!validator.isValid()){
            return ResponseEntity.ok(ResponseUtils.failedResponse("CUSTOMER_NOT_UPDATED", validator.getInvalidFields()));
        }else{
            try{ 
                Customer customer = customerService.update(id, name, surname, cep);
                return ResponseEntity.ok(ResponseUtils.successfullResponse("CUSTOMER_UPDATED", customer));
            } catch (AddressNotFoundException e) {
                return ResponseEntity.ok(ResponseUtils.failedResponse("ADDRESS_NOT_FOUND", "cep"));
            } catch (DataNotFoundException e){
                return ResponseEntity.ok(ResponseUtils.failedResponse("CUSTOMER_NOT_FOUND"));
            }
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try{
            customerService.delete(id);
            return ResponseEntity.ok(ResponseUtils.successfullResponse("CUSTOMER_DELETED", id));
        }catch(DataNotFoundException e){
            return ResponseEntity.ok(ResponseUtils.failedResponse("CUSTOMER_NOT_FOUND"));
        }
    }

}

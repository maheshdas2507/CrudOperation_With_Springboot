package com.api.assignment.crud.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.assignment.crud.entities.Customer;
import com.api.assignment.crud.repository.CustomerRepository;






@RestController
@RequestMapping("/api")
// @CrossOrigin(origins="http://127.0.0.1:5500")
public class CustomerController {
  
   
  @Autowired
  CustomerRepository customerRepository;
   
   @PostMapping("/customers")
   public String createNewCustomer(@RequestBody Customer customer){
    customerRepository.save(customer);

    return "customer created in database";


   }

   @GetMapping("/customers")
   public ResponseEntity<List<Customer>> getAllCustomers(){

    List<Customer> cusList=new ArrayList<>();
    customerRepository.findAll().forEach(cusList::add);
    
    return new ResponseEntity<List<Customer>>(cusList,HttpStatus.OK);
   }
    

   @GetMapping("/customers/{id}")
   public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable  int id){
       Optional<Customer> customer = customerRepository.findById(id);
               
       return ResponseEntity.ok(customer);
   }
 

  

@PutMapping("/customers/{id}")
  public ResponseEntity<String> updateCustomerById(@PathVariable int id, @RequestBody Customer customer) {
    Optional<Customer> cus = customerRepository.findById(id);
    if (cus.isPresent()) {
        Customer existCus = cus.get();
        existCus.setFirst_name(customer.getFirst_name());
        existCus.setLast_name(customer.getLast_name());
        existCus.setStreet(customer.getStreet());
        existCus.setAddress(customer.getAddress());
        existCus.setCity(customer.getCity());
        existCus.setState(customer.getState());
        existCus.setEmail(customer.getEmail());
        existCus.setPhone(customer.getPhone());
        customerRepository.save(existCus);

        return ResponseEntity.ok("Customer Detail against Id " + id + " updated");
    } else {
        return ResponseEntity.notFound().build();
    }
}
   


   @DeleteMapping("/customers/{id}")
   public String deleteCustomerById(@PathVariable int id){
    customerRepository.deleteById(id);
    return "Customer Deleted Successfully";
   } 

  
}

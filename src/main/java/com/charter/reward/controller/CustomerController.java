package com.charter.reward.controller;

import com.charter.reward.entity.CustomerEntity;
import com.charter.reward.entity.PurchaseEntity;
import com.charter.reward.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public List<CustomerEntity> createCustomers(@RequestBody List<CustomerEntity> customers) {
        return this.customerRepository.saveAll(customers);
    }

    @PostMapping("/{customerId}/purchases")
    public ResponseEntity<CustomerEntity> createPurchases(@PathVariable Long customerId,
                                                          @RequestBody List<PurchaseEntity> purchases) {
        CustomerEntity customer = this.customerRepository.findById(customerId).get();
        purchases.forEach(customer::addToPurchases);

        return new ResponseEntity(this.customerRepository.save(customer), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CustomerEntity> getAllCustomers() {
        return this.customerRepository.findAll();
    }
}

package com.charter.reward.controller;


import com.charter.reward.entity.CustomerEntity;
import com.charter.reward.entity.PurchaseEntity;
import com.charter.reward.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerController customerResource;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createCustomersTest() {
        List<CustomerEntity> customers = new ArrayList<>();
        customers.add(new CustomerEntity(1L, "CustomerEntity"));
        customers.add(new CustomerEntity(2L, "Iswor2"));

        when(customerRepository.saveAll(customers)).thenReturn(customers);

        List<CustomerEntity> result = customerResource.createCustomers(customers);
        System.out.println("Result to String: " + result);
        assertNotNull(result);
        System.out.println(result);
        assertEquals(2, result.size());
    }

    @Test
    public void createPurchasesTest() {
        Long customerId = 1L;
        List<PurchaseEntity> purchases = new ArrayList<>();
        purchases.add(new PurchaseEntity(1L, BigDecimal.valueOf(100), Instant.now()));
        purchases.add(new PurchaseEntity(2L, BigDecimal.valueOf(200), Instant.now()));

        CustomerEntity customer = new CustomerEntity(customerId, "CustomerEntity");
        //customer.setPurchases(purchases);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        ResponseEntity<CustomerEntity> result = customerResource.createPurchases(customerId, purchases);
        assertNotNull(result);
        assertEquals(2, result.getBody().getPurchases().size());
    }

    @Test
    public void getAllCustomersTest() {
        List<CustomerEntity> customers = new ArrayList<>();
        customers.add(new CustomerEntity(1L, "CustomerEntity"));
        customers.add(new CustomerEntity(2L, "Iswor2"));

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerEntity> result = customerResource.getAllCustomers();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void createPurchases_whenCustomerFound_savesPurchasesForCustomer() {
        Long customerId = 1L;
        List<PurchaseEntity> purchases = new ArrayList<>();
        purchases.add(new PurchaseEntity(1L, BigDecimal.valueOf(100), Instant.now()));
        CustomerEntity customer = new CustomerEntity();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        customerResource.createPurchases(customerId, purchases);

        assertEquals(1, customer.getPurchases().size());
        assertEquals(purchases, customer.getPurchases());
        Mockito.verify(customerRepository).findById(customerId);
        Mockito.verify(customerRepository).save(customer);
    }

    @Test
    public void createPurchases_whenCustomerFound_returnCreated() {
        Long customerId = 1L;
        List<PurchaseEntity> purchases = new ArrayList<>();
        purchases.add(new PurchaseEntity());

        CustomerEntity customer = new CustomerEntity();
        customer.setId(customerId);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        ResponseEntity<CustomerEntity> response = customerResource.createPurchases(customerId, purchases);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertEquals(customer, response.getBody());
        Mockito.verify(customerRepository).findById(customerId);
        Mockito.verify(customerRepository).save(customer);
    }

}

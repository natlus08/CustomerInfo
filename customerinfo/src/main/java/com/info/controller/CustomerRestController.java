/**
 * 
 */
package com.info.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.exception.CustomerException;
import com.info.model.Customer;
import com.info.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	public static final Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
	
	/**
	 * POST /create --> Create a new customer record and save it in the database.
	 * @throws CustomerException 
	 */
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody Customer customer) throws CustomerException {
		customerService.createCustomer(customer);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	/**
	 * GET /read --> Find a customer by id from the database.
	 * @throws CustomerException 
	 */
	@GetMapping("/read/{id}")
	public ResponseEntity<?> read(@PathVariable("id") String customerId) throws CustomerException {
		Customer customer = customerService.findCustomerByID(customerId);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	/**
	 * PUT /update --> Update a customer record and save it in the database.
	 * @throws CustomerException 
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Customer customer) throws CustomerException {
		customer = customerService.editCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	/**
	 * DELETE /delete --> Delete a customer from the database.
	 * @throws CustomerException 
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String customerId) throws CustomerException {
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}

	/**
	 * GET /read --> Read all customer from the database.
	 */
	@GetMapping("/read-all")
	public ResponseEntity<List<Customer>> readAll() {
		List<Customer> customers = customerService.readAll();
		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<?> customerExceptionHandler(CustomerException exception){
		return new ResponseEntity<Customer>(new Customer(exception.getMessage()),HttpStatus.CONFLICT);
	}
}

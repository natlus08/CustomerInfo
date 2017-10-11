package com.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.exception.CustomerException;
import com.info.model.Customer;
import com.info.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {
		List<Customer> existingCustomer = customerRepository.findByNameAndAddressPhoneNumber(customer.getName(),customer.getAddress().getPhoneNumber());
		if(existingCustomer.isEmpty()){
			customer = customerRepository.save(customer);
		}else{
			throw new CustomerException("Unable to create, A Customer with name " + customer.getName() + " and phone number "+ customer.getAddress().getPhoneNumber() + " already exists.");
		}
		return customer;
	}

	@Override
	public Customer findCustomerByID(String customerId) throws CustomerException {
		Customer customer =	customerRepository.findOne(customerId);
		if (customer == null) {
			throw new CustomerException("Customer with id " + customerId + " not found.");
		}
		return customer;
	}

	@Override
	public Customer editCustomer(Customer customer) throws CustomerException {
		if(customerRepository.exists(customer.getId())){
			List<Customer> existingCustomer = customerRepository.findByNameAndAddressPhoneNumber(customer.getName(),customer.getAddress().getPhoneNumber());
			if(existingCustomer.isEmpty() || existingCustomer.get(0).getId().equalsIgnoreCase(customer.getId())){
				customerRepository.save(customer);
			}else{
				throw new CustomerException("Unable to edit, A Customer with name " + customer.getName() + " and phone number "+ customer.getAddress().getPhoneNumber() + " already exists.");
			}
		}else{
			throw new CustomerException("Unable to Update. Customer with id " + customer.getId() + " not found.");
		}
		return customer;
	}

	@Override
	public void deleteCustomer(String customerId) throws CustomerException{
		Customer customer = customerRepository.findOne(customerId);
		if(null != customer){
			customerRepository.delete(customerId);
		}else{
			throw new CustomerException("Unable to delete. Customer with id " + customerId + " not found.");
		}
	}

	@Override
	public List<Customer> readAll() {
		return customerRepository.findAll();
	}
}

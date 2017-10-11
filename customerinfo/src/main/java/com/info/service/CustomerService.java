package com.info.service;

import java.util.List;

import com.info.exception.CustomerException;
import com.info.model.Customer;

/**
 * 
 * @author 387090
 *
 */
public interface CustomerService {

	Customer createCustomer(Customer customer) throws CustomerException;

	Customer findCustomerByID(String customerId) throws CustomerException;

	Customer editCustomer(Customer customer) throws CustomerException;

	void deleteCustomer(String customerId) throws CustomerException;

	List<Customer> readAll();
}

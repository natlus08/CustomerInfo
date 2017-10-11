package com.info.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.info.model.Customer;
@Transactional
public interface CustomerRepository extends MongoRepository<Customer, String> {
	@Query("{name : ?0, 'address.phoneNumber': ?1}")
	public List<Customer> findByNameAndAddressPhoneNumber(String name,
			String phoneNumber);
}

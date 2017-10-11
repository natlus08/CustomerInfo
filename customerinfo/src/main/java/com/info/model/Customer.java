/**
 * 
 */
package com.info.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.info.model.Address;

/**
 * @author 387090
 *
 */
@Document
public class Customer {
	
	@Id
	private String id;
	@Indexed
	private String name;
	private Address address;
	private String internalName;
	private String status;
	@Transient
	private String errorMessage;
	
	//default constructor
	public Customer(){
		
	}
	
	//Constructor for storing error message
	public Customer(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	//constructor for persistance objects
	@PersistenceConstructor
	public Customer(String id, String name, Address address,
			String internalName, String status) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.internalName = internalName;
		this.status = status;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name.toLowerCase();
	}
	public void setName(String name) {
		this.name = name.toLowerCase();
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getInternalName() {
		return internalName;
	}
	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address
				+ ", internalName=" + internalName + ", status=" + status
				+ ", errorMessage=" + errorMessage + "]";
	}
}

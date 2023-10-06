package com.shopping.esoshop.model;



public class Customer extends User{

	public Customer(){
	}
	public Customer(String name,String address,String phone,String email){
		super(name, address, phone, email);
	}

	@Override
	public String toString() {
		return "Customer [getId()=" + getId() + ", getName()=" + getName() + ", getAddress()=" + getAddress()
				+ ", getPhone()=" + getPhone() + ", getEmail()=" + getEmail() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}

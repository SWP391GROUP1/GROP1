package com.shopping.esoshop.model;

public class Staff extends User{

	@Override
	public String toString() {
		return "Staff [getId()=" + getId() + ", getName()=" + getName() + ", getAddress()=" + getAddress()
				+ ", getPhone()=" + getPhone() + ", getEmail()=" + getEmail() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}

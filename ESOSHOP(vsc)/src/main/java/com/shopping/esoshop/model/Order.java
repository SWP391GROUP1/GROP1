package com.shopping.esoshop.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Order {
	private String orderId;
	private Customer customer;
	private Date orderDate;
	private Staff staff;
	private int status;
	private String address;

	public Order(String orderId, Customer customer, Date orderDate, Staff staff, int status) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.staff = staff;
		this.status = status;
	}

	public Order(String orderId, Customer customer, Date orderDate, Staff staff, int status, String address) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.staff = staff;
		this.status = status;
		this.address = address;
	}

	public Order() {
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String createId(int customerId){
		String id  = "od"+customerId+LocalDateTime.now().toString();
	    id=id.replaceAll("[:,.,-]", "");
		return  id;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", orderDate=" + orderDate + ", staff="
				+ staff + ", status=" + status + ", address=" + address + "]";
	}

}

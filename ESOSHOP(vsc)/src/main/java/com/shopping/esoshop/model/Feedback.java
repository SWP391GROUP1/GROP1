package com.shopping.esoshop.model;

import java.sql.Date;
import java.sql.Time;

public class Feedback {
	
	private Product product;
	
	private Customer customer;
	
	private String commen;
	
	private int star;

	private Date date;
	
	private Time time;

	private int status;

	public Feedback(Product product, Customer customer, String commen, int star, Date date, Time time) {
		super();
		this.product = product;
		this.customer = customer;
		this.commen = commen;
		this.star = star;
		this.date = date;
		this.time = time;
	}

	public Feedback() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCommen() {
		return commen;
	}

	public void setCommen(String commen) {
		this.commen = commen;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Feedback [product=" + product + ", customer=" + customer + ", commen=" + commen + ", star=" + star
				+ ", date=" + date + ", time=" + time + ", status=" + status + "]";
	}

	
}

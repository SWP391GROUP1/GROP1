package com.shopping.esoshop.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Cart {
	private String cartId;
	
	private int customerId;
	
	private Product product;
	
	private int quantity;

	private Date date;
	
	private Time time;
	
	private int colorId;

	public Cart(String cartId, int customerId, Product product, int quantity, Date date, Time time, int colorId) {
		super();
		this.cartId= cartId;
		this.customerId = customerId;
		this.product = product;
		this.quantity = quantity;
		this.date = date;
		this.time = time;
		this.colorId = colorId;
	}

	public Cart() {
        
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	
	public String getColorName() {
		List<Color> colors = this.product.getColor();
		for (Color color : colors) {
			if(color.getColorId()== colorId) {
				return color.getColorname();
			}
		}
		return"No color";
	}
	public String getImage() {
		List<Color> colors = this.product.getColor();
		for (Color color : colors) {
			if(color.getColorId()== colorId) {
				return color.getImage();
			}
		}
		return"No color";
		
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", product=" + product + ", quantity="
				+ quantity + ", date=" + date + ", time=" + time + ", colorId=" + colorId + "]";
	}
	
	
	
	
	
	
	
	
}

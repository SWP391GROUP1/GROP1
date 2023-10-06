package com.shopping.esoshop.model;

import java.time.LocalDateTime;
import java.util.List;

public class Product {

	private String id;

	private String name;

	private List<Color> color;

	private int size;

	private int quantity;

	private double price;

	private String unit;

	private String contents;

	private Supplier supplier;

	private Category category;

	public Product(String id, String name, List<Color> color, int size, int quantity, double price, String unit,
			String contents, Supplier supplier, Category category) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.price = price;
		this.unit = unit;
		this.contents = contents;
		this.supplier = supplier;
		this.category = category;
	}
    public String createId(){
		String id  = "p"+LocalDateTime.now().toString();
	    id=id.replaceAll("[:,.,-]", "");
		return id;
	}
	public Product() {
		super();
	}

	public Product(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Color> getColor() {
		return color;
	}

	public void setColor(List<Color> color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", color=" + color + ", size=" + size + ", quantity=" + quantity
				+ ", price=" + price + ", unit=" + unit + ", contents=" + contents + ", supplier=" + supplier
				+ ", category=" + category + "]";
	}

}

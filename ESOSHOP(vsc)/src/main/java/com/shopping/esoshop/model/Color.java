package com.shopping.esoshop.model;

public class Color {
	String[] colors = {"","Black","While","Yellow"};
	private String productId;
	private int colorId;
	private String image;
	private String colorname;
	
	public Color( int colorId, String image,String colorname) {
		super();
		this.colorId = colorId;
		this.image = image;
		this.colorname = colorname;
	}
	public Color(String productId, int colorId, String image) {
		super();
		this.productId=productId;
		this.colorId = colorId;
		this.image = image;
		this.colorname = colors[colorId];
	}
	public Color() {
		super();
	}
	public String getProductId(){
		return this.productId;
	}
	public void setProductId(String productId){
		this.productId=productId;
	}
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getColorname() {
		return colorname;
	}
	public void setColorname(String colorname) {
		this.colorname = colorname.trim();
	}
//	public String getColor() {
//		String color[] = {"","Black","While","Red"};
//		return color[this.colorId];
//	}

	@Override
	public String toString() {
		return "Color [productId=" + productId + ", colorId=" + colorId + ", image=" + image + ", colorname="
				+ colorname + "]";
	}
	

	
	
}

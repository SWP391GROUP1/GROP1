package com.shopping.esoshop.model;

import java.util.Arrays;

public class ReportRating {
	
	private int Rating[] = new int[6];

	public ReportRating() {
		super();
	}

	public ReportRating(int[] rating) {
		super();
		Rating = rating;
	}

	public int[] getRating() {
		return Rating;
	}

	public void setRating(int[] rating) {
		Rating = rating;
	}

	public int getTotal() {
		int sum = 0;
		for (int i : Rating) {
			sum+=i;
		}
		return sum;
	}
	public int getPercent(int star){
		float p1 = Rating[star];
		float p2 = getTotal();
		return  Math.round((p1/p2)*100);
	}
	@Override
	public String toString() {
		return "ReportRating [Rating=" + Arrays.toString(Rating) + "]";
	}
	
	
	
}

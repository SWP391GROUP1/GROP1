package com.shopping.esoshop.model;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private int active = 1;
	private List<Integer> listpage ;
	public Page(int active, List<Integer> listpage) {
		super();
		this.active = active;
		this.listpage = listpage;
	}
	
	public Page() {
		listpage = new ArrayList<Integer>();
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public List<Integer> getListpage() {
		return listpage;
	}

	public void setListpage(List<Integer> listpage) {
		this.listpage = listpage;
	}

	@Override
	public String toString() {
		return "Page [active=" + active + ", listpage=" + listpage + "]";
	}
	
}

package com.shopping.esoshop.dao;

import java.util.List;


import com.shopping.esoshop.model.*;

public interface Dao {
	
	// product
	public List<Product> getAllProduct();

	public List<Product> getAllProductinPage(int nPage, int sizePage);

	public Page getPage(int npage,int sizePage);

	public Product getProductbyId(String productId);

	public Product insertProduct(Product product);
	
	// category 
	public Category getCategorybyId(int id);
	
	public List<Category> getAllCategory();
	// supplier
	public Supplier getSupplierbyId(int id);
	
	public List<Supplier> getAllSupplier();
	// color
	public List<Color> getColors(String productid);
	public Color insertColors(Color color);
	// account
	public Account getAccount(String email);

	public Account checkLogin(String email,String password);


	public Account createAccount(Account account,Customer customer);

	public List<Account> getAllAccount();
	// customer
	public Customer getCustomerById(int id);

	public Customer getCustomerByEmail(String email);
	
	// cart
	public List<Cart> getCartOfCustomer(int customerId);
	
	public int addToCart(Cart cart);
	
	public int deletCart(String cartId);

	public Cart getCartByCartIdOfCustomer(int customerId,String cartId);

	// feedback
	public List<Feedback> getFeedBack(String productId);
	
	public ReportRating getReportRating(String productID);
	
	// staff
	public Staff getStaffById(int staffId);

	// order 
	public String orderProduct(int customerId,List<OrderDelail> delails,String[] cartIds);
	// order detail
	public List<OrderDelail> getListOrderdetail(String orderId);
	// bill
	public List<Bill> getAllBillsOfCustomer(Customer customer);

	public Bill getBillOfCustomer(Customer customer,String orderId);

	public Bill payBill(Bill bill);
}

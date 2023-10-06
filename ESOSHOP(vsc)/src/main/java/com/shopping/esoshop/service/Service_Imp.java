package com.shopping.esoshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.esoshop.dao.Dao_Imp;
import com.shopping.esoshop.model.*;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class Service_Imp implements DaoService {

	@Autowired
	private Dao_Imp dao;

	@Override
	public List<Product> getAllProduct() {
		return dao.getAllProduct();
	}

	@Override
	public List<Product> getAllProductinPage(int nPage, int sizePage) {
		return dao.getAllProductinPage(nPage, sizePage);
	}

	@Override
	public Page getPage(int npage,int sizePage) {
		return dao.getPage(npage,sizePage);
	}

	@Override
	public Product getProductbyId(String productId) {
		return dao.getProductbyId(productId);
	}

	@Override
	public Account getAccount(String email) {
		return dao.getAccount(email);
	}
	
	@Override
	public Account createAccount(Account account,Customer customer) {
		return dao.createAccount(account,customer);
	}
	@Override
	public Account checkLogin(String email,String password) {
		return dao.checkLogin(email, password);
	}
    
	@Override
	public Customer getCustomerById(int id) {
		return dao.getCustomerById(id);
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return dao.getCustomerByEmail(email);
	}

	@Override
	public List<Account> getAllAccount() {
		
		return dao.getAllAccount();
	}

	@Override
	public List<Cart> getCartOfCustomer(int id) {
		return dao.getCartOfCustomer(id);
	}

	@Override
	public int addToCart(Cart cart) {
		return dao.addToCart(cart);
	}
	
    @Override
    public int deletCart(String cartId) {
    	
    	return dao.deletCart(cartId);
    }
	
	@Override
	public Category getCategorybyId(int id) {
		return dao.getCategorybyId(id);
	}

	@Override
	public Supplier getSupplierbyId(int id) {
		return dao.getSupplierbyId(id);
	}

	@Override
	public List<Color> getColors(String productid) {
		return dao.getColors(productid);
	}

	@Override
	public List<Feedback> getFeedBack(String productId) {
		return dao.getFeedBack(productId);
	}

	@Override
	public ReportRating getReportRating(String productID) {
		return dao.getReportRating(productID);
	}

	@Override
	public List<Category> getAllCategory() {
		return dao.getAllCategory();
	}

	@Override
	public List<Supplier> getAllSupplier() {
		return dao.getAllSupplier();
	}

	@Override
	public Staff getStaffById(int staffId) {
		return dao.getStaffById(staffId);
	}

	@Override
	public Cart getCartByCartIdOfCustomer(int customerId, String cartId) {
		return dao.getCartByCartIdOfCustomer(customerId, cartId);
	}
	@Override
	public String orderProduct(int customerId,List<OrderDelail> delails,String[] cartIds) {
		return dao.orderProduct(customerId,delails,cartIds);
	}
	@Override
	public List<Bill> getAllBillsOfCustomer(Customer customer) {
		return dao.getAllBillsOfCustomer(customer);
	}
	@Override
	public List<OrderDelail> getListOrderdetail(String orderId) {
		return dao.getListOrderdetail(orderId);
	}
	@Override
	public Bill getBillOfCustomer(Customer customer,String orderId) {
		return dao.getBillOfCustomer(customer,orderId);
	}

	@Override
	public Bill payBill(Bill bill) {
		return dao.payBill(bill);
	}

	@Override
	public Product insertProduct(Product product) {
		return dao.insertProduct(product);
	}

	@Override
	public Color insertColors(Color color) {
		return dao.insertColors(color);
	}
}

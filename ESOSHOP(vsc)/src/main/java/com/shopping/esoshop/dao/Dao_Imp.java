package com.shopping.esoshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shopping.esoshop.model.*;
import com.shopping.esoshop.utils.DBHelper;

@Repository
public class Dao_Imp implements Dao {

	// @Autowired
	// private JdbcTemplate Template;

	// product
	@Override
	public List<Product> getAllProduct() {
		String sql = "SELECT [ProductID]\r\n" + //
				"      ,[ProductName]\r\n" + //
				"      ,[Size]\r\n" + //
				"      ,[Quantity]\r\n" + //
				"      ,[Price]\r\n" + //
				"      ,[Unit]\r\n" + //
				"      ,[Contents]\r\n" + //
				"      ,[SupplierID]\r\n" + //
				"      ,[CategoryID]\r\n" + //
				"  FROM [dbo].[products]";
		try {
			List<Product> list = new ArrayList<Product>();
			PreparedStatement psm = DBHelper.makeConnection().prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("ProductID"));
				p.setName(rs.getString("ProductName"));
				p.setColor(getColors(rs.getString("ProductID")));
				p.setSize(rs.getInt("Size"));
				p.setQuantity(rs.getInt("Quantity"));
				p.setPrice(rs.getDouble("Price"));
				p.setUnit(rs.getString("Unit"));
				p.setContents(rs.getString("Contents"));
				p.setCategory(getCategorybyId(rs.getInt("CategoryID")));
				p.setSupplier(getSupplierbyId(rs.getInt("SupplierID")));
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getAllProductinPage(int nPage, int sizePage) {
		String sql = "SELECT * FROM [dbo].[products]";
		try {
			List<Product> list = new ArrayList<Product>();
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, (nPage - 1) * sizePage);
			psm.setInt(2, sizePage);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("ProductID"));
				p.setName(rs.getString("ProductName"));
				p.setColor(getColors(rs.getString("ProductID")));
				p.setSize(rs.getInt("Size"));
				p.setQuantity(rs.getInt("Quantity"));
				p.setPrice(rs.getDouble("Price"));
				p.setUnit(rs.getString("Unit"));
				p.setContents(rs.getString("Contents"));
				p.setCategory(getCategorybyId(rs.getInt("CategoryID")));
				p.setSupplier(getSupplierbyId(rs.getInt("SupplierID")));
				list.add(p);
			}
			conn.close();
			psm.close();
			rs.close();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product getProductbyId(String productId) {
		String sql = "SELECT * FROM [dbo].[products] where ProductID = ?";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, productId);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("ProductID"));
				p.setName(rs.getString("ProductName"));
				p.setColor(getColors(rs.getString("ProductID")));
				p.setSize(rs.getInt("Size"));
				p.setQuantity(rs.getInt("Quantity"));
				p.setPrice(rs.getDouble("Price"));
				p.setUnit(rs.getString("Unit"));
				p.setContents(rs.getString("Contents"));
				p.setCategory(getCategorybyId(rs.getInt("CategoryID")));
				p.setSupplier(getSupplierbyId(rs.getInt("SupplierID")));
				return p;
			}
			conn.close();
			psm.close();
			rs.close();
		} catch (Exception e) {
			return new Product();
		}
		return new Product();
	}

	@Override
	public Product insertProduct(Product product) {
		String sql = "INSERT INTO [dbo].[products]\r\n" + //
				"           ([ProductID]\r\n" + //
				"           ,[ProductName]\r\n" + //
				"           ,[Size]\r\n" + //
				"           ,[Quantity]\r\n" + //
				"           ,[Price]\r\n" + //
				"           ,[Unit]\r\n" + //
				"           ,[Contents]\r\n" + //
				"           ,[SupplierID]\r\n" + //
				"           ,[CategoryID])\r\n" + //
				"     VALUES(?,?,?,?,?,?,?,?,?)";
				try {
					Connection conn = DBHelper.makeConnection();
					PreparedStatement psm = conn.prepareStatement(sql);
					psm.setString(1, product.getId());
					psm.setString(2, product.getName());
					psm.setInt(3, product.getSize());
					psm.setInt(4, product.getQuantity());
					psm.setDouble(5, product.getPrice());
					psm.setString(6, product.getUnit());
					psm.setString(7, product.getContents());
					psm.setInt(8, product.getSupplier().getId());
					psm.setInt(9, product.getCategory().getId());
					if(psm.executeUpdate()>0){
						return product;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
		return null;
	}
	// get list of page
	@Override
	public Page getPage(int npage,int sizePage) {
		String sql = "select count(*) from products";
		try {
			Page page = new Page();
			page.setActive(npage);
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				int nPage = rs.getInt(1) / sizePage;
				if (rs.getInt(1) % sizePage > 0) {
					nPage = nPage + 1;
				}
				for (int i = 1; i <= nPage; i++) {
					page.getListpage().add(i);
				}
				return page;
			}
			conn.close();
			psm.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// category
	@Override
	public Category getCategorybyId(int id) {
		String sql = "select * from categories where CategoryID = " + id;
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Category ca = new Category();
				ca.setId(rs.getInt(1));
				ca.setName(rs.getString(2));
				ca.setDescription(rs.getString(3));
				return ca;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Category> getAllCategory() {
		String sql = "select * from categories ";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			List<Category> categories = new ArrayList<Category>();
			while(rs.next()) {
				Category ca = new Category();
				ca.setId(rs.getInt(1));
				ca.setName(rs.getString(2));
				ca.setDescription(rs.getString(3));
				categories.add(ca);
			}
			conn.close();
			psm.close();
			rs.close();
			return categories;
		} catch (Exception e) {
		}
		
		return null;
	}

	// supplier
	@Override
	public Supplier getSupplierbyId(int id) {
		String sql = "select * from suppliers where SupplierID=" + id;
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Supplier sp = new Supplier();
				sp.setId(id);
				sp.setName(rs.getString(2));
				sp.setAddress(rs.getString(3));
				sp.setPhone(rs.getString(4));
				return sp;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public List<Supplier> getAllSupplier() {
	    String sql = "SELECT [SupplierID]\r\n"
	    		+ "      ,[SupplierName]\r\n"
	    		+ "      ,[Address]\r\n"
	    		+ "      ,[Phone]\r\n"
	    		+ "  FROM [dbo].[suppliers]";
	    try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			List<Supplier> suppliers = new ArrayList<>();
			while(rs.next()) {
				Supplier sp = new Supplier();
				sp.setId(rs.getInt("SupplierID"));
				sp.setName(rs.getString("SupplierName"));
				sp.setAddress(rs.getString("Address"));
				sp.setPhone(rs.getString("Phone"));
				suppliers.add(sp);
			}
			return suppliers;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	// color
	@Override
	public List<Color> getColors(String productid) {
		String sql = "SELECT [ProductID]\r\n" + //
				"      ,[ColorID]\r\n" + //
				"      ,[Image]\r\n" + //
				"      ,[ColorName]\r\n" + //
				"  FROM [dbo].[color] where ProductID =?";
		try {
			List<Color> colors = new ArrayList<>();
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, productid);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Color color = new Color();
				color.setProductId(rs.getString("ProductID"));
				color.setColorId(rs.getInt("ColorID"));
				color.setImage(rs.getString("Image"));
				color.setColorname(rs.getString("ColorName"));
				colors.add(color);
			}
			conn.close();
			psm.close();
			rs.close();
			return colors;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Color insertColors(Color color) {
		String sql = "INSERT INTO [dbo].[color]\r\n" + //
				"           ([ProductID]\r\n" + //
				"           ,[ColorID]\r\n" + //
				"           ,[Image]\r\n" + //
				"           ,[ColorName])\r\n" + //
				"     VALUES(?,?,?,?)";
				try {
					Connection conn = DBHelper.makeConnection();
					PreparedStatement psm = conn.prepareStatement(sql);
					psm.setString(1, color.getProductId());
					psm.setInt(2, color.getColorId());
					psm.setString(3, color.getImage());
					psm.setString(4, color.getColorname());
					int n = psm.executeUpdate();
					if(n>0){
                        return color;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
		return null;
	}
	// account
	@Override
	public Account getAccount(String email) {
		String sql = "select * from accounts where Email = '" + email + "'";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Account a = new Account();
				a.setEmail(email);
				a.setPassword(rs.getString(2));
				a.setRole(rs.getInt(3));
				a.setStatus(rs.getInt(4));
				return a;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Account checkLogin(String email,String password) {
		String sql = "select * from accounts where Email = ? and Password = ? and Status =1";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, email);
			psm.setString(2, password);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Account a = new Account();
				a.setEmail(email);
				a.setPassword(rs.getString(2));
				a.setRole(rs.getInt(3));
				a.setStatus(rs.getInt(4));
				return a;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Account> getAllAccount() {
		String sql = "SELECT [Email]\r\n" + //
				"      ,[Password]\r\n" + //
				"      ,[Role]\r\n" + //
				"      ,[Status]\r\n" + //
				"  FROM [dbo].[accounts]";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			List<Account> accounts = new ArrayList<>();
			while(rs.next()){
				Account a = new Account();
				a.setEmail(rs.getString("Email"));
				a.setPassword(rs.getString("Password"));
				a.setRole(rs.getInt("Role"));
				a.setStatus(rs.getInt("Status"));
				accounts.add(a);
			}
			conn.close();
			psm.close();
			rs.close();
			return accounts;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
	
	@Override
	public Account createAccount(Account account,Customer customer) {
		String sql1 = "INSERT INTO [dbo].[accounts]\r\n" + //
				"           ([Email]\r\n" + //
				"           ,[Password]\r\n" + //
				"           ,[Role]\r\n" + //
				"           ,[Status])\r\n" + //
				"     VALUES(?,?,?,?)";
		String sql2 = "INSERT INTO [dbo].[customers]\r\n" + //
				"           ([CustomerName]\r\n" + //
				"           ,[Address]\r\n" + //
				"           ,[PhoneNumber]\r\n" + //
				"           ,[Email])\r\n" + //
				"     VALUES(?,?,?,?)";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm ;
			// insert account
			psm = conn.prepareStatement(sql1);
			psm.setString(1, account.getEmail());
			psm.setString(2, account.getPassword());
		    psm.setInt(3,account.getRole());
			psm.setInt(4, account.getStatus());
			int resutl1= psm.executeUpdate();
			if(resutl1>0){
				// insert customer infor;
				psm.clearParameters();
				psm=conn.prepareStatement(sql2);
				psm.setString(1, customer.getName());
				psm.setString(2, customer.getAddress());
				psm.setString(3, customer.getPhone());
				psm.setString(4, customer.getEmail());
				int resutl2 = psm.executeUpdate();
				if(resutl2 >0){
					return account;
				}
			}
		} catch (Exception e) {
		
		}
		return null;
	}
	
	// customer
	@Override
	public Customer getCustomerById(int id) {
		String sql = "SELECT [CustomerID]\r\n"
				+ "      ,[CustomerName]\r\n"
				+ "      ,[Address]\r\n"
				+ "      ,[PhoneNumber]\r\n"
				+ "      ,[Email]\r\n"
				+ "  FROM [dbo].[customers]"
				+ "  where CustomerID = ?";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, id);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt("CustomerID"));
				c.setName(rs.getString("CustomerName"));
				c.setAddress(rs.getString("Address"));
				c.setPhone(rs.getString("PhoneNumber"));
				c.setEmail(rs.getString("Email"));
				return  c;
			}
			conn.close();
			psm.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
	
	@Override
	public Customer getCustomerByEmail(String email) {
		String sql = "SELECT [CustomerID]\r\n"
				+ "      ,[CustomerName]\r\n"
				+ "      ,[Address]\r\n"
				+ "      ,[PhoneNumber]\r\n"
				+ "      ,[Email]\r\n"
				+ "  FROM [dbo].[customers]"
				+ "  where Email = ?";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, email);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt("CustomerID"));
				c.setName(rs.getString("CustomerName"));
				c.setAddress(rs.getString("Address"));
				c.setPhone(rs.getString("PhoneNumber"));
				c.setEmail(rs.getString("Email"));
				return  c;
			}
			conn.close();
			psm.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	// cart
	@Override
	public List<Cart> getCartOfCustomer(int customerId) {
		String sql = "SELECT [CartID]\r\n"
				+ "      ,[CustomerID]\r\n"
				+ "      ,[ProductID]\r\n"
				+ "      ,[Quantity]\r\n"
				+ "      ,[Time]\r\n"
				+ "      ,[Color]\r\n"
				+ "  FROM [dbo].[carst]"
				+ "  where CustomerID = ?" 
				+ "  Order by  Time desc";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, customerId);
			ResultSet rs = psm.executeQuery();
			List<Cart> carts = new ArrayList<Cart>();
			while (rs.next()) {
				Cart c = new Cart();
				c.setCartId(rs.getString("CartID"));
				c.setCustomerId(rs.getInt("CustomerID"));
				c.setProduct(getProductbyId(rs.getString("ProductID")));
				c.setQuantity(rs.getInt("Quantity"));
				c.setDate(rs.getDate("Time"));
				c.setTime(rs.getTime("Time"));
				c.setColorId(rs.getInt("Color"));
				carts.add(c);
			}
			return carts;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Cart getCartByCartIdOfCustomer(int customerId, String cartId) {
		String sql = "SELECT [CartID]\r\n"
				+ "      ,[CustomerID]\r\n"
				+ "      ,[ProductID]\r\n"
				+ "      ,[Quantity]\r\n"
				+ "      ,[Time]\r\n"
				+ "      ,[Color]\r\n"
				+ "  FROM [dbo].[carst]"
				+ "  where CustomerID = ? and CartID =?" 
				+ "  Order by  Time desc";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, customerId);
			psm.setString(2, cartId);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Cart c = new Cart();
				c.setCartId(rs.getString("CartID"));
				c.setCustomerId(rs.getInt("CustomerID"));
				c.setProduct(getProductbyId(rs.getString("ProductID")));
				c.setQuantity(rs.getInt("Quantity"));
				c.setDate(rs.getDate("Time"));
				c.setTime(rs.getTime("Time"));
				c.setColorId(rs.getInt("Color"));
				return c;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	@Override
	public int addToCart(Cart cart) {
		String sql = "INSERT INTO [dbo].[carst]\r\n" + //
				"           ([CartID]\r\n" + //
				"           ,[CustomerID]\r\n" + //
				"           ,[ProductID]\r\n" + //
				"           ,[Quantity]\r\n" + //
				"           ,[Time]\r\n" + //
				"           ,[Color])\r\n" + //
				"     VALUES(?,?,?,?,GETDATE(),?)";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			String cartId = cart.getCustomerId()+"-"+cart.getProduct().getId()+"-"+cart.getColorId();
			psm.setString(1, cartId.trim());
			psm.setInt(2, cart.getCustomerId());
			psm.setString(3, cart.getProduct().getId());
			psm.setInt(4, cart.getQuantity());
			psm.setInt(5, cart.getColorId());
			int n = psm.executeUpdate();
			return n;

		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public int deletCart(String cartId) {
		String sql = "DELETE FROM [dbo].[carst]\r\n"
				+ "      WHERE CartID = ?";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, cartId);
			return psm.executeUpdate();
		} catch (Exception e) {

		}
		return 0;
	}

	// feedback
	@Override
	public List<Feedback> getFeedBack(String productId) {
		String sql = "SELECT [ProductID]\r\n"
				+ "      ,[CustomerID]\r\n"
				+ "      ,[Commen]\r\n"
				+ "      ,[Star]\r\n"
				+ "      ,[Time]\r\n"
				+ "      ,[Status]\r\n"
				+ "  FROM [dbo].[feedbacks]"
				+ " where ProductID = ? and Status =1";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, productId);
			ResultSet rs = psm.executeQuery();
			List<Feedback> feedbacks = new ArrayList<Feedback>();
			while (rs.next()) {
				Feedback fb = new Feedback();
				fb.setProduct(getProductbyId(productId));
				fb.setCustomer(getCustomerById(rs.getInt("CustomerID")));
				fb.setCommen(rs.getString("Commen"));
				fb.setStar(rs.getInt("Star"));
				fb.setDate(rs.getDate("Time"));
				fb.setTime(rs.getTime("Time"));
				feedbacks.add(fb);
			}
			return feedbacks;
		} catch (Exception e) {
		}
		return null;

	}

	@Override
	public ReportRating getReportRating(String productID) {
		String sql = "select Star, count(Star) from feedbacks where ProductID  = ? and Status =1\r\n" 
	            + "GROUP BY Star \r\n"
				+ "order by Star desc";
		try {
			ReportRating reportRating = new ReportRating();
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, productID);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				reportRating.getRating()[rs.getInt(1)] = rs.getInt(2);
			}
			return reportRating;
		} catch (Exception e) {
		}
		System.out.println("loi");
		return null;
	}
	
	// staff
	@Override
	public Staff getStaffById(int staffId) {
		String sql = "SELECT [StaffID]\r\n"
				+ "      ,[Name]\r\n"
				+ "      ,[Email]\r\n"
				+ "  FROM [dbo].[staffs]\r\n"
				+ "  Where StaffID = ?";
		try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, staffId);
			ResultSet rs = psm.executeQuery();
			if(rs.next()) {
				Staff staff = new Staff();
				staff.setId(rs.getInt("StaffID"));
				staff.setName(rs.getString("Name"));
				staff.setEmail(rs.getString("Email"));
				return staff;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	// order
	// order from order_detail
	@Override
	public String orderProduct(int customerId,List<OrderDelail> delails,String[] cartIds) {
		Connection conn = DBHelper.makeConnection();
		PreparedStatement psm ;
		Customer customer = getCustomerById(customerId);
		String sql1 = "INSERT INTO [dbo].[orders]\r\n" + //
				"           ([OrderID]\r\n" + //
				"           ,[CustomerID]\r\n" + //
				"           ,[OrderDate]\r\n" + //
				"           ,[StaffID]\r\n" + //
				"           ,[Status]\r\n" + //
				"           ,[Address])\r\n" + //
				"     VALUES(?,?,GETDATE(),0,0,?)";
	    String sql2 ="INSERT INTO [dbo].[order_details]\r\n" + //
	    		"           ([OrderDetailID]\r\n" + //
	    		"           ,[OrderID]\r\n" + //
	    		"           ,[ProductID]\r\n" + //
	    		"           ,[Quantity]\r\n" + //
	    		"           ,[Color]\r\n" + //
	    		"           ,[Price])\r\n" + //
	    		"     VALUES(?,?,?,?,?,?)";
		try {
			Order order = new Order();
			String orderId = order.createId(customer.getId());
			psm=conn.prepareStatement(sql1);
			psm.setString(1, orderId);
			psm.setInt(2, customer.getId());
			psm.setString(3, customer.getAddress());
			int n = psm.executeUpdate();
			int c = 0;
			if(n>0){
				psm.clearParameters();
				for (OrderDelail de : delails) {
					psm = conn.prepareStatement(sql2);
					psm.setString(1, de.createId(customerId));
					psm.setString(2, orderId);
					psm.setString(3, de.getProduct().getId());
					psm.setInt(4, de.getQuantity());
					psm.setInt(5, de.getColor());
					psm.setDouble(6, de.getProduct().getPrice());
				    if(psm.executeUpdate()>0){
                        TimeUnit.MILLISECONDS.sleep(10);
						psm.clearParameters();
						deletCart(cartIds[c]);
						c++;
					}
				}
				
			}
			return orderId;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	// orderdetail
	@Override
	public List<OrderDelail> getListOrderdetail(String orderId) {
		String sql = "SELECT [OrderDetailID]\r\n" + //
				"      ,[OrderID]\r\n" + //
				"      ,[ProductID]\r\n" + //
				"      ,[Quantity]\r\n" + //
				"      ,[Color]\r\n" + //
				"      ,[Price]\r\n" + //
				"  FROM [dbo].[order_details] where OrderID =? ";
				List<OrderDelail> orderdelails = new ArrayList<>();
				try {
					Connection conn = DBHelper.makeConnection();
					PreparedStatement psm = conn.prepareStatement(sql);
					psm.setString(1, orderId);
					ResultSet rs = psm.executeQuery();
					while(rs.next()){
						OrderDelail ord = new OrderDelail();
						ord.setOrderDetailId(rs.getString("OrderDetailID"));
						ord.setOrderId(rs.getString("OrderID"));
						ord.setProduct(getProductbyId(rs.getString("ProductID")));
						ord.setQuantity(rs.getInt("Quantity"));
						ord.setColor(rs.getInt("Color"));
						ord.setPrice(rs.getDouble("Price"));
						orderdelails.add(ord);
					}
					return orderdelails;
				} catch (Exception e) {
					// TODO: handle exception
				}
		return null;
	}

	// bill
	@Override
	public List<Bill> getAllBillsOfCustomer(Customer customer) {
		String sql = "SELECT [OrderID]\r\n" + //
						"      ,[CustomerID]\r\n" + //
						"      ,[OrderDate]\r\n" + //
						"      ,[StaffID]\r\n" + //
						"      ,[Status]\r\n" + //
						"      ,[Address]\r\n" + //
						"  FROM [dbo].[orders] where CustomerID = ? and Status =1;";
	    try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, customer.getId());
            ResultSet rs = psm.executeQuery();
			List<Bill> bills = new ArrayList<>();
			while(rs.next()){
				Bill b = new Bill();
				b.setOrderId(rs.getString("OrderID"));
				b.setCustomer(customer);
				b.setOrderdetails(getListOrderdetail(rs.getString("OrderID")));
				b.setOrderDate(rs.getDate("OrderDate"));
				b.setStatus(rs.getInt("Status"));
				b.setAddress(rs.getString("Address"));
				bills.add(b);
			}
			return bills;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Bill getBillOfCustomer(Customer customer,String orderId) {
				String sql = "SELECT [OrderID]\r\n" + //
						"      ,[CustomerID]\r\n" + //
						"      ,[OrderDate]\r\n" + //
						"      ,[StaffID]\r\n" + //
						"      ,[Status]\r\n" + //
						"      ,[Address]\r\n" + //
						"  FROM [dbo].[orders] where CustomerID = ? and OrderID=?;";
	    try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, customer.getId());
			psm.setString(2, orderId);
            ResultSet rs = psm.executeQuery();
			if(rs.next()){
				Bill b = new Bill();
				b.setOrderId(orderId);
				b.setCustomer(customer);
				b.setOrderdetails(getListOrderdetail(rs.getString("OrderID")));
				b.setOrderDate(rs.getDate("OrderDate"));
				b.setStatus(rs.getInt("Status"));
				b.setAddress(rs.getString("Address"));
				return b;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	private Bill getBillById(String orderId){
				String sql = "SELECT [OrderID]\r\n" + //
						"      ,[CustomerID]\r\n" + //
						"      ,[OrderDate]\r\n" + //
						"      ,[StaffID]\r\n" + //
						"      ,[Status]\r\n" + //
						"      ,[Address]\r\n" + //
						"  FROM [dbo].[orders] where OrderID=?";
	    try {
			Connection conn = DBHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, orderId);
            ResultSet rs = psm.executeQuery();
			if(rs.next()){
				Bill b = new Bill();
				b.setOrderId(orderId);
				b.setCustomer(getCustomerById(rs.getInt("CustomerID")));
				b.setOrderdetails(getListOrderdetail(rs.getString("OrderID")));
				b.setOrderDate(rs.getDate("OrderDate"));
				b.setStatus(rs.getInt("Status"));
				b.setAddress(rs.getString("Address"));
				return b;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
    @Override 
	public Bill payBill(Bill bill){
		String sql = "UPDATE [dbo].[orders]\r\n" + //
				"     SET [Status] = 1\r\n" + //
				"     WHERE OrderID =?";
				try {
					Connection conn = DBHelper.makeConnection();
					PreparedStatement psm = conn.prepareStatement(sql);
					psm.setString(1, bill.getOrderId());
					int update = psm.executeUpdate();
					if(update>0){
						return getBillById(bill.getOrderId());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
		return bill;
	}
}

package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.esoshop.model.Cart;
import com.shopping.esoshop.model.Customer;
import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	
	@Autowired
	private DaoService daoService;
	
	// view cart of customer
	@GetMapping("/cart")
	public String getCart(Model model,HttpSession session) {
		Customer customer  = (Customer)session.getAttribute("customer");
		if(customer!=null) {
			model.addAttribute("carts", daoService.getCartOfCustomer(customer.getId()));
			return "cart";
		}
		return "login";
	}
	
	// add product to cart
	@RequestMapping("/addtocart")
	public String addTocart(Model model,HttpSession session,
			@RequestParam(name = "product_id") String id,
			@RequestParam(name = "product_quanity",defaultValue = "1")Integer quantity,
			@RequestParam(name = "product_color") Integer color){
		Customer customer = (Customer)session.getAttribute("customer");
		if(customer!=null) {
			Cart c = new Cart();
			c.setCustomerId(customer.getId());
			c.setProduct(daoService.getProductbyId(id));
			c.setQuantity(quantity);
			c.setColorId(color);
		    int n =daoService.addToCart(c);
		    session.setAttribute("added",c.getCustomerId()+"-"+c.getProduct().getId()+"-"+c.getColorId());
			if(n>0){
				return "redirect:/cart";
			}
		}
		return "redirect:/detail"+id;
	}
	
	// delete product form cart
	@GetMapping("/detete_cart{cart_id}")
	public String deleteProductOfCart(Model model,HttpSession session,
			@PathVariable("cart_id") String cartId) {
		Customer customer = (Customer)session.getAttribute("customer");
		if(customer!=null) {
			// delete product of cart
		   int n = daoService.deletCart(cartId);
		   // delete succes
		   if(n>0) {
			   return "redirect:/cart";
		   }
		}
		return "redirect:/login";
	}
	
}

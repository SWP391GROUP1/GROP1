package com.shopping.esoshop.controller.customer;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.esoshop.model.*;
import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	
	@Autowired
	private DaoService daoService;

	// order product
	@RequestMapping("/order")
	public String order(Model model,HttpSession session,
			@RequestParam(name = "product_order",defaultValue = "0", required = false)String cartId,
			@RequestParam(name = "product_quantity",defaultValue = "",required = false)String quantity) {
			Customer customer = (Customer)session.getAttribute("customer");
		if(cartId.equals("0")) {
			return "redirect:/cart";
		}
		else {
			try {
				String[] cartIDs = cartId.split(",");
				String[] quantitys = quantity.split(",");
				List<OrderDelail> orderDelails = new ArrayList<OrderDelail>();
				for (int i=0;i< cartIDs.length;i++) {
					// get information cart
					Cart c = daoService.getCartByCartIdOfCustomer(customer.getId(), cartIDs[i]);
					OrderDelail od = new OrderDelail();
					od.setOrderDetailId(od.createId(customer.getId()));
					od.setProduct(c.getProduct());
					od.setQuantity(Integer.parseInt(quantitys[i]));
					od.setColor(c.getColorId());
					orderDelails.add(od);
					System.out.println(od);
				}
				String orderId = daoService.orderProduct(customer.getId(), orderDelails, cartIDs);
				System.out.println(orderId);
				return "redirect:/bill"+orderId+"";
			} catch (Exception e) {
			}
		}
		return "redirect:/cart";
	}

}

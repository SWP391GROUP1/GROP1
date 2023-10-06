package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DetailController {

	@Autowired
	private DaoService daoService;
	

	@GetMapping("/detail{id}")
	public String getProduct(Model model,HttpSession session,
			@PathVariable("id")String id) {
		// product
		model.addAttribute("product", daoService.getProductbyId(id));
		// feeback of product
		model.addAttribute("feedbacks",daoService.getFeedBack(id));
		// report rating product
		model.addAttribute("ratings", daoService.getReportRating(id));
		// save session url to back add to cart more
		String urlback = "/detail"+id+"";
		if((String)session.getAttribute("urlback")==null){
			urlback="redirect:/home";
		}
		session.setAttribute("urlback", urlback);
		return "detail";
	}
	
	
}

package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping.esoshop.service.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
    private int sizePage = 20;
    
	@Autowired
	private DaoService daoService;
	
	@GetMapping(value = {"/","index","home"})
	public String getHome(Model model,HttpSession session) {
		// session.setAttribute("account",  daoService.getAccount("linhndhe163822@fpt.edu.vn"));
		// session.setAttribute("customer", daoService.getCustomerByEmail("linhndhe163822@fpt.edu.vn"));
		model.addAttribute("pages", daoService.getPage(1,sizePage));
		model.addAttribute("products", daoService.getAllProduct());
		model.addAttribute("categories",daoService.getAllCategory());
		model.addAttribute("suppliers", daoService.getAllSupplier());
		return"index";
	}
	
	@GetMapping("/page{n}")
	public String getPage(Model model,
			@PathVariable("n")Integer nPage) {
		model.addAttribute("pages", daoService.getPage(nPage,sizePage));
		model.addAttribute("products", daoService.getAllProduct());
		model.addAttribute("categories",daoService.getAllCategory());
		model.addAttribute("suppliers", daoService.getAllSupplier());
		return"index";
	}
	
	@GetMapping("/account")
	public String getajax(Model model) {
		model.addAttribute("products", daoService.getAllProduct());
		return "account";
	}
	

}

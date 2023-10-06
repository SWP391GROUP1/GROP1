package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.esoshop.model.Account;
import com.shopping.esoshop.model.Customer;
// import com.shopping.esoshop.model.User;
import com.shopping.esoshop.service.DaoService;
import com.shopping.esoshop.service.MailService;
import com.shopping.esoshop.utils.Capcha;

import jakarta.servlet.http.HttpSession;

@Controller
public class SecurityController {

	public final static String Account="account";
	public final static String Customer="customer";
	
	private String otp="";
	private Account newAccount = null ;
	private Customer newCustomer = null;

	@Autowired
	private MailService mailService;

	@Autowired
	private DaoService dao;

	// for register
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
	

	@RequestMapping("/register_verify")
	public String doRegister(HttpSession session, Model model,
			@RequestParam(name = "email", defaultValue = "", required = false) String email,
			@RequestParam(name = "name", defaultValue = "", required = false) String name,
			@RequestParam(name = "phone", defaultValue = "", required = false) String phone,
			@RequestParam(name = "address", defaultValue = "", required = false) String address,
			@RequestParam(name = "password", defaultValue = "", required = false) String password) {
		Account account = dao.getAccount(email);
		if (account == null) {
			try {
				otp = Capcha.createCapcha();
				newAccount = new Account(email, password, 1,1);
				newCustomer = new Customer(name, address, phone, email);
				session.setAttribute("newCustomer", newCustomer);
				// send otp
				mailService.sendEmail(newAccount.getEmail(), "Verify account", "Your OTP: " + otp);
				return "register_verify";
			} catch (Exception e) {
				e.printStackTrace();
				return "register";
			}
		} else {
			model.addAttribute("mess", email + " was registed");
			return "register";
		}
	}
	
	@PostMapping("/register_verify_OTP")
	public String verifyAccountRegister(HttpSession session,
			@RequestParam(name = "OTP", required = false, defaultValue = "") String OTP) {
		if (OTP.equals(otp)) {
			// Account newAccount = (Account) session.getAttribute("newAccount");
			// Customer newCustomer = (Customer) session.getAttribute("newCustomer");
			dao.createAccount(newAccount, newCustomer);
			// after register login for customer
			session.setAttribute(Account,  newAccount);
		    session.setAttribute(Customer, newCustomer);
			return "redirect:/index";
		} else {
			return "register_verify";
		}
	}

	// for login

	@GetMapping("/login")
	public String getLogin(HttpSession session) {
		Account account = (Account) session.getAttribute(Account);
		if (account != null) {
			return "redirect:/index";
		}
		return "login";
	}

	@RequestMapping(value = "/login_password",method = RequestMethod.POST)
	public String loginByPassword(Model model, HttpSession session,
			@RequestParam(name = "email", required = false, defaultValue = "") String email,
			@RequestParam(name = "password",required = false,defaultValue = "") String password) {
		Account account = dao.checkLogin(email, password);
		if (account != null) {
			session.setAttribute(Account,  account);
		    session.setAttribute(Customer, dao.getCustomerByEmail(email));
			return "redirect:/home";
		} else {
			model.addAttribute("mess", "Cannot found account");
			return "login";
		}
	}
   
	@RequestMapping(value = "/login_otp",method = RequestMethod.POST)
	public String loginByOtp(Model model, HttpSession session,
	@RequestParam(name = "email", required = false, defaultValue = "") String email){
	    otp = Capcha.createCapcha();
		session.setAttribute("email", email);
		mailService.sendEmail(email, "Login", "this is your otp: "+otp);
		model.addAttribute("OTP", otp);
		return "login";
	}

	@RequestMapping("/verify_otp")
	public String verifyAccountLogin(HttpSession session,
			@RequestParam(name = "OTP", required = false, defaultValue = "") String OTP) {
		if (OTP.equals(otp)) {
			loginSession(session,(String)session.getAttribute("email"));
			return "redirect:/home";
		} else {
			return "/login";
		}
	}
	
	public void loginSession(HttpSession session,String email) {
		session.setAttribute(Account,  dao.getAccount(email));
		session.setAttribute(Customer, dao.getCustomerByEmail(email));
	}

	// for logout
	@GetMapping("/logout")
	public String logOut(HttpSession session){
		session.setAttribute(Account, null);
		session.setAttribute(Customer, null);
		return"redirect:/home";
	}
}

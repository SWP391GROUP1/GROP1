package com.shopping.esoshop.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping.esoshop.model.Customer;
import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PayController {

    @Autowired 
    private DaoService daoService;

    @GetMapping(value = "/bill{orderId}")
    public String showBill(Model model, HttpSession session,
        @PathVariable(value = "orderId" )String orderId) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            model.addAttribute("bill", daoService.getBillOfCustomer(customer,orderId));
        } else {
            return "redirect:/login";
        }
        return "bill";
    }
    
    @GetMapping(value = "/pay{orderId}")
    public String pay(Model model,HttpSession session,
        @PathVariable(value = "orderId")String orderId){
        Customer customer = (Customer) session.getAttribute("customer");
            if(customer!=null){
                daoService.payBill(daoService.getBillOfCustomer(customer, orderId));
                
            }
        return "redirect:/bill"+orderId;
    }

    
}

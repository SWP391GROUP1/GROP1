package com.shopping.esoshop.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class FeedbackController {
    
    @GetMapping("/feedback{productId}")
    public String doFeedback(HttpSession session,Model model){
        model.addAttribute("productFeedback", null);
        return "feedback";
    }

     @GetMapping("/feedback")
    public String showFeedback(){
     
        return "feedback";
    }
}

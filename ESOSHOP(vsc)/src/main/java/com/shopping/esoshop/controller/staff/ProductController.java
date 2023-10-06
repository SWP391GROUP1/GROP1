package com.shopping.esoshop.controller.staff;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.esoshop.model.*;

import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {


    @Autowired
    private DaoService daoService;
    
    // show manager product
    @GetMapping(value={"/staff/productmanages","/staff"})
    public String getMethodName(Model model,HttpSession session) {
        model.addAttribute("products", daoService.getAllProduct());
        return "/staff/productmanages";
    }

    // show view add product
    @GetMapping( value = "staff/addproduct")
    public String getViewaddProduct(Model model){
        model.addAttribute("categories",daoService.getAllCategory());
        model.addAttribute("suppliers", daoService.getAllSupplier());
        return "/staff/addproduct";
    }

    Product newproduct = new Product();
    
    @PostMapping(value = "/staff/createproduct")
    public String createProduct(Model model,HttpSession session,
        @RequestParam(value = "productName",defaultValue = "")String name,
        @RequestParam(value = "productContent",defaultValue = "")String content,
        @RequestParam(value = "category",defaultValue = "1")Integer category,
        @RequestParam(value = "supplier",defaultValue = "1")Integer supplier,
        @RequestParam(value = "price",defaultValue = "1")String price,
        @RequestParam(value = "unit",defaultValue = "")String unit,
        @RequestParam(value = "quantity",defaultValue = "3000")String quantity){
            Product product = new Product();
            product.setId(product.createId());
            product.setName(name);
            product.setContents(content);
            product.setCategory(daoService.getCategorybyId(category));
            product.setSupplier(daoService.getSupplierbyId(supplier));
            product.setPrice(Double.parseDouble(price));
            product.setUnit(unit);
            product.setQuantity(Integer.parseInt(quantity));
            daoService.insertProduct(product);
            session.setAttribute("newproduct", product);
        return "/staff/addproductdetail";
    }
}

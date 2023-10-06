package com.shopping.esoshop.controller.staff;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.esoshop.model.Color;
import com.shopping.esoshop.model.Product;
import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class uploadImage {

    @Autowired
    private DaoService daoService;

    @PostMapping("/staff/uploadimage")
    public String createColor(Model model,HttpSession session,
        @RequestParam("colorId") Integer colorId,
        @RequestParam("img") MultipartFile img) {
        Product newproduct = (Product)session.getAttribute("newproduct");
        System.out.println(img.getOriginalFilename());
        String finame = newproduct.getId()+"_"+colorId+".webp";
        Color color = new Color(newproduct.getId(), colorId, finame);
        daoService.insertColors(color);
        upload(img,finame);
        return "redirect:/staff/addproductdetail"+newproduct.getId();
    }

    @GetMapping("/staff/addproductdetail{productId}")
    public String getUpLoad(Model model,
        @PathVariable("productId")String productId) {
        model.addAttribute("colors", daoService.getColors(productId));
        return "/staff/addproductdetail";
    }

    private void upload(MultipartFile img,String filename){
        String url = System.getProperty("user.dir");
        url+="/src/main/resources/static/img/";
        Path path = Paths.get(url);
        try {
           InputStream inputStream = img.getInputStream();
           Files.copy(inputStream, path.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

package com.netcracker.controllers;

import com.netcracker.models.Book;
import com.netcracker.models.Customer;
import com.netcracker.models.Purchase;
import com.netcracker.models.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMain(){
        return "main";
    }

    @GetMapping("/book")
    public String getBook(Model model){
        model.addAttribute("book", new Book());
        return "book";
    }

    @GetMapping("/customer")
    public String getCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customer";
    }

    @GetMapping("/shop")
    public String getShop(Model model){
        model.addAttribute("shop", new Shop());
        return "shop";
    }

    @GetMapping("/purchase")
    public String getPurchase(Model model){
        model.addAttribute("purchase", new Purchase());
        return "purchase";
    }

}

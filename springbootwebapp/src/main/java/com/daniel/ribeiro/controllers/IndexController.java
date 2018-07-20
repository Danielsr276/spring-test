package com.daniel.ribeiro.controllers;

import com.daniel.ribeiro.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping("/")
    String index(Model model) {
        model.addAttribute("restaurants", restaurantService.listAllRestaurants());
        return "restaurants";
    }
}

package com.daniel.ribeiro.controllers;

import com.daniel.ribeiro.domain.Professional;
import com.daniel.ribeiro.domain.Restaurant;
import com.daniel.ribeiro.services.ProfessionalService;
import com.daniel.ribeiro.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class RestaurantController {

    private RestaurantService restaurantService;

    private ProfessionalService professionalService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setProfessionalService(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }


    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("restaurants", restaurantService.listAllRestaurants());
        return "restaurants";
    }

    @RequestMapping("restaurant/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("restaurant", restaurantService.getRestaurantById(id));
        return "restaurantShow";
    }

    @RequestMapping("restaurant/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("professionals", professionalService.listAllProfessionals());
        model.addAttribute("restaurant", restaurantService.getRestaurantById(id));
        return "restaurantForm";
    }

    @RequestMapping(value = "restaurant", method = RequestMethod.POST)
    public String save(Restaurant restaurant, @RequestParam Integer idProfessional) {
        Professional professional = professionalService.getProfessionalById(idProfessional);
        if (idProfessional != null) {
            if (restaurant.getProfessionalList() == null) {
                restaurant.setProfessionalList(new ArrayList<>());
            }

            // adiciona a relação
            professional.setRestaurant(restaurant);
            restaurant.getProfessionalList().add(professional);
            restaurant.setNumberVotes(restaurant.getNumberVotes() + 1);
        }

        restaurantService.saveRestaurant(restaurant);
        professionalService.saveProfessional(professional);

        return "redirect:/restaurants";
    }
}

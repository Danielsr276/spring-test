package com.daniel.ribeiro.services;


import com.daniel.ribeiro.domain.Restaurant;

public interface RestaurantService {
    Iterable<Restaurant> listAllRestaurants();

    Restaurant getRestaurantById(Integer id);

    Restaurant saveRestaurant(Restaurant restaurant);
}

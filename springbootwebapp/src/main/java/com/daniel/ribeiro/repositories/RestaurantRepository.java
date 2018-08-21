package com.daniel.ribeiro.repositories;

import com.daniel.ribeiro.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{

    Iterable<Restaurant> findAllByOrderByNumberVotesDesc();
}

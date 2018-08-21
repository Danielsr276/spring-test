package com.daniel.ribeiro.bootstrap;

import com.daniel.ribeiro.domain.Restaurant;
import com.daniel.ribeiro.repositories.RestaurantRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RestaurantLoader implements ApplicationListener<ContextRefreshedEvent> {

    private RestaurantRepository restaurantRepository;

    private Logger log = LogManager.getLogger(RestaurantLoader.class);

    @Autowired
    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (int i = 0; i < 5; i++) {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(i);
            restaurant.setName("Restaurante " + i);
            restaurant.setNumberVotes(0);
            restaurant.setCreatedAt(new Date());
            restaurant.setUpdatedAt(new Date());

            restaurantRepository.save(restaurant);

            log.info("Saved New Restaurant - id: " + restaurant.getRestId());
        }
    }
}

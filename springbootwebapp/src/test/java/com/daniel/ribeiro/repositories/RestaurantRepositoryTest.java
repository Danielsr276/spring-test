package com.daniel.ribeiro.repositories;

import com.daniel.ribeiro.configuration.RepositoryConfiguration;
import com.daniel.ribeiro.domain.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class RestaurantRepositoryTest {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Test
    public void testRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1000);
        restaurant.setName("Restaurante teste");
        restaurant.setNumberVotes(0);

        //save , verify has ID value after save
        assertNull(restaurant.getRestId()); //null before save
        restaurantRepository.save(restaurant);
        assertNotNull(restaurant.getRestId()); //not null after save
        //fetch from DB
        Restaurant fetchedRestaurant = restaurantRepository.findById(restaurant.getRestId()).orElse(null);

        //should not be null
        assertNotNull(fetchedRestaurant);

        //should equal
        assertEquals(restaurant.getRestId(), fetchedRestaurant.getRestId());
        assertEquals(restaurant.getName(), fetchedRestaurant.getName());

        //update description and save
        fetchedRestaurant.setName("New Description");
        restaurantRepository.save(fetchedRestaurant);

        //get from DB, should be updated
        Restaurant fetchedUpdatedRestaurant = restaurantRepository.findById(fetchedRestaurant.getRestId()).orElse(null);
        assertEquals(fetchedRestaurant.getName(), fetchedUpdatedRestaurant.getName());

        //verify count in DB
        long count = restaurantRepository.count();
        assertEquals(count, 1);

        //get all products, list should only have one
        Iterable<Restaurant> restaurants = restaurantRepository.findAll();

        count = 0;

        for (Restaurant r : restaurants) {
            count++;
        }

        assertEquals(count, 1);
    }
}

package com.daniel.ribeiro.repositories;

import com.daniel.ribeiro.configuration.RepositoryConfiguration;
import com.daniel.ribeiro.domain.Restaurant;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;

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
        restaurant.setCreatedAt(new Date());
        restaurant.setUpdatedAt(new Date());

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

        Thread t = new Thread(() -> System.out.println(
                "\n\n ###################################################################### " +
                        "\n Hello world \n " +
                        "###################################################################### \n\n"));

        t.run();

        System.out.println("\n\n ###################################################################### \n\n");
        Arrays.asList(1, 2, 3, 4, 5).forEach(System.out::println);
        System.out.println("\n\n ###################################################################### \n\n");

        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        System.out.println("\n\n ###################################################################### \n\n");
        System.out.println(evenNumbers.test(1000));
        System.out.println("\n\n ###################################################################### \n\n");

        IntPredicate oddNumbers = (int i) -> i % 2 == 1;
        System.out.println("\n\n ###################################################################### \n\n");
        System.out.println(oddNumbers.test(1000));
        System.out.println("\n\n ###################################################################### \n\n");


        Function<BufferedReader, String> f =
                (BufferedReader b) -> {
                    try {
                        return b.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                };

    }

    public interface IntPredicate {
        boolean test(int t);
    }
}

package com.minidoordash.services;

import com.minidoordash.models.MenuItem;
import com.minidoordash.models.Restaurant;
import com.minidoordash.repositories.MenuItemRepository;
import com.minidoordash.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class DataInitializationService implements CommandLineRunner {
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private MenuItemRepository menuItemRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Only initialize if no restaurants exist
        if (restaurantRepository.count() == 0) {
            initializeDemoData();
        }
    }
    
    private void initializeDemoData() {
        // Create 5 demo restaurants
        List<Restaurant> restaurants = Arrays.asList(
            createRestaurant("Burger King", "Fast Food", "American burgers and fries", 
                           "123 Main St, City", "555-0101", 4.2, 150, 2.99, 15.0, true),
            createRestaurant("Tofu House", "Korean", "Authentic Korean cuisine", 
                           "456 Oak Ave, City", "555-0102", 4.5, 89, 3.99, 25.0, true),
            createRestaurant("Fashion Work", "Gourmet", "Upscale dining experience", 
                           "789 Pine St, City", "555-0103", 4.7, 67, 4.99, 35.0, true),
            createRestaurant("Pizza Palace", "Italian", "Traditional Italian pizzas", 
                           "321 Elm St, City", "555-0104", 4.3, 112, 2.49, 20.0, true),
            createRestaurant("Sushi Express", "Japanese", "Fresh sushi and Asian cuisine", 
                           "654 Maple Dr, City", "555-0105", 4.6, 78, 3.49, 30.0, true)
        );
        
        // Save restaurants
        restaurants = restaurantRepository.saveAll(restaurants);
        
        // Create menu items for each restaurant
        createMenuItemsForRestaurant(restaurants.get(0), "Burger King"); // Burger King
        createMenuItemsForRestaurant(restaurants.get(1), "Tofu House");  // Tofu House
        createMenuItemsForRestaurant(restaurants.get(2), "Fashion Work"); // Fashion Work
        createMenuItemsForRestaurant(restaurants.get(3), "Pizza Palace"); // Pizza Palace
        createMenuItemsForRestaurant(restaurants.get(4), "Sushi Express"); // Sushi Express
    }
    
    private Restaurant createRestaurant(String name, String cuisineType, String description, 
                                      String address, String phone, Double rating, 
                                      Integer totalRatings, Double deliveryFee, 
                                      Double minimumOrder, Boolean isOpen) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setCuisineType(cuisineType);
        restaurant.setDescription(description);
        restaurant.setAddress(address);
        restaurant.setPhoneNumber(phone);
        restaurant.setRating(BigDecimal.valueOf(rating));
        restaurant.setTotalRatings(totalRatings);
        restaurant.setDeliveryFee(BigDecimal.valueOf(deliveryFee));
        restaurant.setMinimumOrder(BigDecimal.valueOf(minimumOrder));
        restaurant.setOpen(isOpen);
        restaurant.setActive(true);
        restaurant.setEstimatedDeliveryTime(30);
        restaurant.setImageUrl("https://via.placeholder.com/300x200/FF6B35/FFFFFF?text=" + name.replace(" ", "+"));
        return restaurant;
    }
    
    private void createMenuItemsForRestaurant(Restaurant restaurant, String restaurantName) {
        List<MenuItem> menuItems = null;
        
        switch (restaurantName) {
            case "Burger King":
                menuItems = Arrays.asList(
                    createMenuItem("Whopper", "Flame-grilled beef patty with fresh lettuce, tomatoes, mayo, pickles, and onions", 
                                 BigDecimal.valueOf(8.99), "Burgers", restaurant, 450, 15, false, false, false),
                    createMenuItem("Chicken Royale", "Crispy chicken breast with lettuce and creamy mayo", 
                                 BigDecimal.valueOf(7.99), "Chicken", restaurant, 380, 12, false, false, false),
                    createMenuItem("French Fries", "Golden crispy fries seasoned with salt", 
                                 BigDecimal.valueOf(3.99), "Sides", restaurant, 220, 8, true, true, true),
                    createMenuItem("Chicken Nuggets", "Crispy chicken nuggets with dipping sauce", 
                                 BigDecimal.valueOf(5.99), "Chicken", restaurant, 320, 10, false, false, false),
                    createMenuItem("Onion Rings", "Crispy battered onion rings", 
                                 BigDecimal.valueOf(4.99), "Sides", restaurant, 280, 8, true, true, false)
                );
                break;
                
            case "Tofu House":
                menuItems = Arrays.asList(
                    createMenuItem("Spicy Tofu Soup", "Traditional Korean spicy tofu soup with vegetables", 
                                 BigDecimal.valueOf(12.99), "Soups", restaurant, 280, 20, true, true, true),
                    createMenuItem("Bibimbap", "Mixed rice bowl with vegetables, egg, and gochujang sauce", 
                                 BigDecimal.valueOf(14.99), "Rice Bowls", restaurant, 420, 15, true, true, true),
                    createMenuItem("Kimchi Fried Rice", "Fried rice with kimchi, vegetables, and egg", 
                                 BigDecimal.valueOf(11.99), "Rice Dishes", restaurant, 380, 12, true, true, true),
                    createMenuItem("Bulgogi", "Marinated beef with rice and vegetables", 
                                 BigDecimal.valueOf(16.99), "Main Dishes", restaurant, 520, 18, false, false, true),
                    createMenuItem("Japchae", "Stir-fried glass noodles with vegetables", 
                                 BigDecimal.valueOf(13.99), "Noodles", restaurant, 340, 15, true, true, true)
                );
                break;
                
            case "Fashion Work":
                menuItems = Arrays.asList(
                    createMenuItem("Gourmet Burger", "Premium beef burger with artisanal cheese and special sauce", 
                                 BigDecimal.valueOf(16.99), "Burgers", restaurant, 580, 20, false, false, false),
                    createMenuItem("Truffle Fries", "Crispy fries with truffle oil and parmesan cheese", 
                                 BigDecimal.valueOf(8.99), "Sides", restaurant, 320, 10, true, true, false),
                    createMenuItem("Caesar Salad", "Fresh romaine lettuce with caesar dressing and croutons", 
                                 BigDecimal.valueOf(13.99), "Salads", restaurant, 180, 8, true, true, false),
                    createMenuItem("Wagyu Steak", "Premium Japanese Wagyu beef with seasonal vegetables", 
                                 BigDecimal.valueOf(45.99), "Steaks", restaurant, 720, 25, false, false, false),
                    createMenuItem("Lobster Mac & Cheese", "Creamy mac and cheese with fresh lobster", 
                                 BigDecimal.valueOf(22.99), "Pasta", restaurant, 480, 18, false, false, false)
                );
                break;
                
            case "Pizza Palace":
                menuItems = Arrays.asList(
                    createMenuItem("Margherita Pizza", "Classic pizza with tomato sauce, mozzarella, and basil", 
                                 BigDecimal.valueOf(12.99), "Pizzas", restaurant, 320, 15, true, true, false),
                    createMenuItem("Pepperoni Pizza", "Pizza topped with pepperoni and mozzarella", 
                                 BigDecimal.valueOf(14.99), "Pizzas", restaurant, 380, 15, false, false, false),
                    createMenuItem("BBQ Chicken Pizza", "Pizza with BBQ sauce, chicken, and red onions", 
                                 BigDecimal.valueOf(16.99), "Pizzas", restaurant, 420, 18, false, false, false),
                    createMenuItem("Garlic Bread", "Toasted bread with garlic butter and herbs", 
                                 BigDecimal.valueOf(5.99), "Sides", restaurant, 180, 8, true, true, false),
                    createMenuItem("Caesar Salad", "Fresh romaine with caesar dressing", 
                                 BigDecimal.valueOf(8.99), "Salads", restaurant, 150, 8, true, true, false)
                );
                break;
                
            case "Sushi Express":
                menuItems = Arrays.asList(
                    createMenuItem("California Roll", "Crab, avocado, and cucumber roll", 
                                 BigDecimal.valueOf(8.99), "Rolls", restaurant, 220, 10, false, false, true),
                    createMenuItem("Salmon Nigiri", "Fresh salmon over seasoned rice", 
                                 BigDecimal.valueOf(6.99), "Nigiri", restaurant, 180, 8, false, false, true),
                    createMenuItem("Spicy Tuna Roll", "Spicy tuna and cucumber roll", 
                                 BigDecimal.valueOf(9.99), "Rolls", restaurant, 240, 10, false, false, true),
                    createMenuItem("Miso Soup", "Traditional Japanese miso soup", 
                                 BigDecimal.valueOf(3.99), "Soups", restaurant, 80, 5, true, true, true),
                    createMenuItem("Edamame", "Steamed soybeans with sea salt", 
                                 BigDecimal.valueOf(4.99), "Appetizers", restaurant, 120, 5, true, true, true)
                );
                break;
        }
        
        if (menuItems != null) {
            menuItemRepository.saveAll(menuItems);
        }
    }
    
    private MenuItem createMenuItem(String name, String description, BigDecimal price, String category, 
                                  Restaurant restaurant, Integer calories, Integer prepTime, 
                                  Boolean isVegetarian, Boolean isVegan, Boolean isGlutenFree) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setPrice(price);
        menuItem.setCategory(category);
        menuItem.setRestaurant(restaurant);
        menuItem.setCalories(calories);
        menuItem.setPreparationTime(prepTime);
        menuItem.setVegetarian(isVegetarian);
        menuItem.setVegan(isVegan);
        menuItem.setGlutenFree(isGlutenFree);
        menuItem.setAvailable(true);
        menuItem.setImageUrl("https://via.placeholder.com/200x150/FF6B35/FFFFFF?text=" + name.replace(" ", "+"));
        return menuItem;
    }
} 
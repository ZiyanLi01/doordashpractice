package com.minidoordash.controllers;

import com.minidoordash.models.Restaurant;
import com.minidoordash.repositories.RestaurantRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "Restaurant Management", description = "APIs for restaurant operations")
@CrossOrigin(origins = "*")
public class RestaurantController {
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    // Get all restaurants
    @GetMapping
    @Operation(summary = "Get all restaurants", description = "Retrieve all active restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findByIsActiveTrue();
        return ResponseEntity.ok(restaurants);
    }
    
    // Get restaurant by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by ID", description = "Retrieve restaurant information by ID")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        return restaurantRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Search restaurants
    @GetMapping("/search")
    @Operation(summary = "Search restaurants", description = "Search restaurants by name or cuisine")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam String keyword) {
        List<Restaurant> restaurants = restaurantRepository.searchRestaurants(keyword);
        return ResponseEntity.ok(restaurants);
    }
    
    // Get restaurants by cuisine type
    @GetMapping("/cuisine/{cuisineType}")
    @Operation(summary = "Get restaurants by cuisine", description = "Retrieve restaurants by cuisine type")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCuisine(@PathVariable String cuisineType) {
        List<Restaurant> restaurants = restaurantRepository.findByCuisineTypeIgnoreCase(cuisineType);
        return ResponseEntity.ok(restaurants);
    }
} 
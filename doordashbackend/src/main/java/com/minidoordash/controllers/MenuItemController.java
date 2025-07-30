package com.minidoordash.controllers;

import com.minidoordash.models.MenuItem;
import com.minidoordash.repositories.MenuItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-items")
@Tag(name = "Menu Item Management", description = "APIs for menu item operations")
@CrossOrigin(origins = "*")
public class MenuItemController {
    
    @Autowired
    private MenuItemRepository menuItemRepository;
    
    // Get all menu items
    @GetMapping
    @Operation(summary = "Get all menu items", description = "Retrieve all available menu items")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findByIsAvailableTrue();
        return ResponseEntity.ok(menuItems);
    }
    
    // Get menu item by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get menu item by ID", description = "Retrieve menu item information by ID")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Get menu items by restaurant
    @GetMapping("/restaurant/{restaurantId}")
    @Operation(summary = "Get menu items by restaurant", description = "Retrieve all menu items for a specific restaurant")
    public ResponseEntity<List<MenuItem>> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        List<MenuItem> menuItems = menuItemRepository.findByRestaurantIdAndIsAvailableTrue(restaurantId);
        return ResponseEntity.ok(menuItems);
    }
    
    // Get menu items by category
    @GetMapping("/category/{category}")
    @Operation(summary = "Get menu items by category", description = "Retrieve menu items by category")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> menuItems = menuItemRepository.findByCategoryIgnoreCaseAndIsAvailableTrue(category);
        return ResponseEntity.ok(menuItems);
    }
    
    // Search menu items
    @GetMapping("/search")
    @Operation(summary = "Search menu items", description = "Search menu items by name or description")
    public ResponseEntity<List<MenuItem>> searchMenuItems(@RequestParam String keyword) {
        List<MenuItem> menuItems = menuItemRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword);
        return ResponseEntity.ok(menuItems);
    }
} 
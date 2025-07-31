package com.minidoordash.repositories;

import com.minidoordash.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    
    // Find available menu items
    List<MenuItem> findByIsAvailableTrue();
    
    // Find menu items by restaurant ID
    List<MenuItem> findByRestaurantId(Long restaurantId);
    
    // Find available menu items by restaurant ID
    List<MenuItem> findByRestaurantIdAndIsAvailableTrue(Long restaurantId);
    
    // Find menu items by category
    List<MenuItem> findByCategoryIgnoreCase(String category);
    
    // Find available menu items by category
    List<MenuItem> findByCategoryIgnoreCaseAndIsAvailableTrue(String category);
    
    // Search menu items by name or description
    @Query("SELECT m FROM MenuItem m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(m.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<MenuItem> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(@Param("keyword") String keyword);
    
    // Find menu items by price range
    List<MenuItem> findByPriceBetweenAndIsAvailableTrue(Double minPrice, Double maxPrice);
    
    // Find vegetarian menu items
    List<MenuItem> findByIsVegetarianTrueAndIsAvailableTrue();
    
    // Find vegan menu items
    List<MenuItem> findByIsVeganTrueAndIsAvailableTrue();
    
    // Find gluten-free menu items
    List<MenuItem> findByIsGlutenFreeTrueAndIsAvailableTrue();
} 
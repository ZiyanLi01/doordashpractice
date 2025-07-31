package com.minidoordash.repositories;

import com.minidoordash.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
    // Find restaurants by name
    List<Restaurant> findByNameContainingIgnoreCase(String name);
    
    // Find restaurants by cuisine type
    List<Restaurant> findByCuisineTypeIgnoreCase(String cuisineType);
    
    // Find active restaurants
    List<Restaurant> findByIsActiveTrue();
    
    // Find open restaurants
    List<Restaurant> findByIsOpenTrue();
    
    // Find active and open restaurants
    List<Restaurant> findByIsActiveTrueAndIsOpenTrue();
    
    // Find restaurants by owner
    List<Restaurant> findByOwnerId(Long ownerId);
    
    // Find restaurants with minimum rating
    List<Restaurant> findByRatingGreaterThanEqual(BigDecimal rating);
    
    // Find restaurants by delivery fee range
    List<Restaurant> findByDeliveryFeeBetween(BigDecimal minFee, BigDecimal maxFee);
    
    // Find restaurants by minimum order amount
    List<Restaurant> findByMinimumOrderLessThanEqual(BigDecimal maxOrder);
    
    // Custom query to find restaurants by address
    @Query("SELECT r FROM Restaurant r WHERE r.address LIKE %:address%")
    List<Restaurant> findByAddressContaining(@Param("address") String address);
    
    // Custom query to find restaurants with specific criteria
    @Query("SELECT r FROM Restaurant r WHERE r.isActive = true AND r.isOpen = true AND " +
           "(r.name LIKE %:keyword% OR r.cuisineType LIKE %:keyword% OR r.description LIKE %:keyword%)")
    List<Restaurant> searchRestaurants(@Param("keyword") String keyword);
    
    // Find restaurants ordered by rating (highest first)
    @Query("SELECT r FROM Restaurant r WHERE r.isActive = true ORDER BY r.rating DESC")
    List<Restaurant> findTopRatedRestaurants();
} 
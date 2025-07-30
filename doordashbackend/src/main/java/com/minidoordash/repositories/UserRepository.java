package com.minidoordash.repositories;

import com.minidoordash.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find user by username
    Optional<User> findByUsername(String username);
    
    // Find user by email
    Optional<User> findByEmail(String email);
    
    // Check if username exists
    boolean existsByUsername(String username);
    
    // Check if email exists
    boolean existsByEmail(String email);
    
    // Find users by role
    List<User> findByRole(User.UserRole role);
    
    // Find active users
    List<User> findByIsActiveTrue();
    
    // Find users by role and active status
    List<User> findByRoleAndIsActiveTrue(User.UserRole role);
    
    // Custom query to find users with specific criteria
    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.fullName LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<User> searchUsers(@Param("keyword") String keyword);
    
    // Find users by delivery address (for location-based queries)
    @Query("SELECT u FROM User u WHERE u.deliveryAddress LIKE %:address%")
    List<User> findByDeliveryAddressContaining(@Param("address") String address);
} 
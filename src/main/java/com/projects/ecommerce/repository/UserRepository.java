package com.projects.ecommerce.repository;

import com.projects.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    public User findByEmail(String email);
    public User findByPhone(String phone);

    boolean existsByEmail(User user);
}

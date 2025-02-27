package com.jvg.ExpenseTracker.repository;

import com.jvg.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository {
    Optional<User> findByEmail(String email);
}

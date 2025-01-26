package com.jvg.ExpenseTracker.repository;

import com.jvg.ExpenseTracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
}

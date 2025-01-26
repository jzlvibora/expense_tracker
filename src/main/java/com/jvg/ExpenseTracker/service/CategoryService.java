package com.jvg.ExpenseTracker.service;

import com.jvg.ExpenseTracker.dto.CategoryDTO;
import com.jvg.ExpenseTracker.dto.ExpenseDTO;
import com.jvg.ExpenseTracker.model.Category;
import com.jvg.ExpenseTracker.model.Expense;
import com.jvg.ExpenseTracker.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryDTO categoryDTO) {
        return save(categoryDTO);
    }

    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.get().setName(categoryDTO.getName());
            return category.get();
        } else {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
    }

    private Category save(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll().stream()
                .sorted(Comparator.comparing(Category::getName))
                .collect(Collectors.toList());
    }

    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
    }

    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
    }

}

package com.jvg.ExpenseTracker.service;

import com.jvg.ExpenseTracker.dto.ExpenseDTO;
import com.jvg.ExpenseTracker.model.Category;
import com.jvg.ExpenseTracker.model.Expense;
import com.jvg.ExpenseTracker.repository.CategoryRepository;
import com.jvg.ExpenseTracker.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public Expense createExpense(ExpenseDTO expenseDTO) {
        return saveExpense(new Expense(), expenseDTO);
    }

    private Expense saveExpense(Expense expense, ExpenseDTO expenseDTO) {
        Category category = categoryRepository.findByName(expenseDTO.getCategoryName());
        expense.setTitle(expenseDTO.getTitle());
        expense.setDescription(expenseDTO.getDescription());
        expense.setCategory(category);
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            return expense.get();
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " not found");
        }
    }

    public Expense updateExpense(Long id, ExpenseDTO expenseDTO) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            return saveExpense(expense.get(), expenseDTO);
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " not found");
        }
    }

    public void deleteExpense(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            expenseRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " not found");
        }
    }

    public List<ExpenseDTO> getExpenseByCategory(Long categoryId) {
        List<Expense> expenses = expenseRepository.findByCategoryId(categoryId);
        return expenses.stream().map(expense -> new ExpenseDTO(
                expense.getId(),
                expense.getTitle(),
                expense.getDescription(),
                expense.getCategory().getName(),
                expense.getDate(),
                expense.getAmount()
        )).collect(Collectors.toList());
    }
}

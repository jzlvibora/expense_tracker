package com.jvg.ExpenseTracker.controller;

import com.jvg.ExpenseTracker.dto.ExpenseDTO;
import com.jvg.ExpenseTracker.model.Expense;
import com.jvg.ExpenseTracker.service.ExpenseService;
import com.jvg.ExpenseTracker.service.PDFGeneratorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final PDFGeneratorService pdfGeneratorService;

    @PostMapping
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDTO expenseDTO) {
        Expense newExpense = expenseService.createExpense(expenseDTO);
        if (newExpense != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newExpense);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

    @GetMapping
    public ResponseEntity<?> getExpenseById(@RequestParam Long id) {
        try {
            Expense expense = expenseService.getExpenseById(id);
            return ResponseEntity.status(HttpStatus.OK).body(expense);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateExpense(@RequestParam Long id, @RequestBody ExpenseDTO expenseDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(expenseService.updateExpense(id, expenseDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteExpense(@RequestParam Long id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PostMapping("/report")
    public void generatePdf() throws Exception {
        pdfGeneratorService.generatePdfReport();
    }

    @GetMapping("/category")
    public ResponseEntity<?> getExpensesByCategory(Long id) {
        List<ExpenseDTO> expenses = expenseService.getExpenseByCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

}

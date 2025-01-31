package com.jvg.ExpenseTracker.service;

import com.jvg.ExpenseTracker.dto.GraphDTO;
import com.jvg.ExpenseTracker.dto.StatsDTO;
import com.jvg.ExpenseTracker.model.Expense;
import com.jvg.ExpenseTracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final ExpenseRepository expenseRepository;

    public GraphDTO getChartData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);
        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));

        return graphDTO;
    }

    public StatsDTO getStats() {
        Double totalExpense = expenseRepository.sumAllAmount();
        Optional<Expense> expense = expenseRepository.findFirstByOrderByDateDesc();
        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        if (expense.isPresent()) {
            statsDTO.setLatestExpense(expense.get());
        }
        return statsDTO;
    }
}

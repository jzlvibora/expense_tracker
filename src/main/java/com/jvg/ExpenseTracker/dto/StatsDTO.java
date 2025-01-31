package com.jvg.ExpenseTracker.dto;

import com.jvg.ExpenseTracker.model.Expense;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StatsDTO {
    private Double expense;
    private Expense latestExpense;
}

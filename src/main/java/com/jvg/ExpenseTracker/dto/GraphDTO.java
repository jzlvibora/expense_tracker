package com.jvg.ExpenseTracker.dto;


import com.jvg.ExpenseTracker.model.Expense;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {
    private List<Expense> expenseList;
}

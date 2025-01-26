package com.jvg.ExpenseTracker.dto;

import com.jvg.ExpenseTracker.model.Category;
import lombok.*;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private Long id;

    private String title;

    private String description;

    private String categoryName;

    private LocalDate date;

    private Integer amount;

}

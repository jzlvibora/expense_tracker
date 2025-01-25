package com.jvg.ExpenseTracker.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ExpenseDTO {
    private Long id;

    private String title;

    private String description;

    private String category;

    private LocalDate date;

    private Integer amount;


}

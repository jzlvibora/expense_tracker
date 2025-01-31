package com.jvg.ExpenseTracker.controller;

import com.jvg.ExpenseTracker.dto.GraphDTO;
import com.jvg.ExpenseTracker.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin("*")
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @GetMapping("/chart")
    public ResponseEntity<GraphDTO> getChartDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(statsService.getChartData());
    }

    @GetMapping
    public ResponseEntity<?> getStats() {
        return ResponseEntity.status(HttpStatus.OK).body(statsService.getStats());
    }
}

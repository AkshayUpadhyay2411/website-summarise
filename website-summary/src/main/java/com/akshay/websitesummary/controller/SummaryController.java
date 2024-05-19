package com.akshay.websitesummary.controller;

import com.akshay.websitesummary.dto.SummaryRequestDto;
import com.akshay.websitesummary.dto.SummaryResponseDto;
import com.akshay.websitesummary.service.SummaryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@NoArgsConstructor
@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @PostMapping("/add")
    public SummaryResponseDto getSummary(@RequestBody SummaryRequestDto urlBody) {
        return summaryService.getSummary(urlBody);
    }

    @GetMapping("/all")
    public List<SummaryResponseDto> getAllSummary() {
        return summaryService.getAllSummary();
    }
}

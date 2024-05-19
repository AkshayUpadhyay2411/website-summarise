package com.akshay.websitesummary.client;

import com.akshay.websitesummary.dto.SummaryRequestDto;
import com.akshay.websitesummary.dto.SummaryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="scala-service" , url = "http://127.0.0.1:8080")
public interface ScalaServiveClient {

    @PostMapping("/summaries")
    SummaryResponseDto getSummary(@RequestBody SummaryRequestDto urlBody);

    @GetMapping("/summaries")
    List<SummaryResponseDto> getAllSummary();

}

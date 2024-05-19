package com.akshay.websitesummary.service;

import com.akshay.websitesummary.client.ScalaServiveClient;
import com.akshay.websitesummary.dto.SummaryRequestDto;
import com.akshay.websitesummary.dto.SummaryResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class SummaryService {

    @Autowired
    private ScalaServiveClient scalaServiveClient;

    public SummaryService(ScalaServiveClient scalaServiveClient) {
        this.scalaServiveClient = scalaServiveClient;
    }

    public SummaryResponseDto getSummary(SummaryRequestDto urlBody) {
        return scalaServiveClient.getSummary(urlBody);
    }

    public List<SummaryResponseDto> getAllSummary() {
        return scalaServiveClient.getAllSummary();
    }
}

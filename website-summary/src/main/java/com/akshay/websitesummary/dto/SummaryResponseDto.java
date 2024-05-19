package com.akshay.websitesummary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SummaryResponseDto implements Serializable {
    String url;
    String summary;
    String id;
}

package com.diu.edu.demoservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleSubDTO {
    private Long id;
    private SaleMainDTO saleMain;
    private ItemInfoDTO itemInfo;
    private Double price;
    private Integer quantity;
}

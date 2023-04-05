package com.microservices.io.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Integer quantity;
    private String skuCode;
    private BigDecimal itemUnitPrice;
    private BigDecimal discountPerItem;
    private BigDecimal totalPrice;
    private BigDecimal totalDiscount;
    private boolean active;
}

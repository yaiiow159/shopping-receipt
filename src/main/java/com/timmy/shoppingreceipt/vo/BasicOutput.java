package com.timmy.shoppingreceipt.vo;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BasicOutput implements Serializable {
    private String productName;
    private String locationName;
    private String categoryName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal; // subtotal = price * quantity
    private BigDecimal tax; // tax = price * quantity * taxRate
    private BigDecimal taxRate;
    private BigDecimal total; // total = subtotal + tax
}

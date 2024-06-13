package com.timmy.shoppingreceipt.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    @NotBlank(message = "商品名稱不得為空")
    private String name;

    @NotEmpty(message = "商品價格不得為空")
    @DecimalMin(value = "0.01", message = "商品價格不得小於 0.01")
    private BigDecimal price;

    @NotEmpty(message = "商品數量不得為空")
    private int quantity;

    private int locationId;

    private int categoryId;

    private boolean isTaxExempt;
}

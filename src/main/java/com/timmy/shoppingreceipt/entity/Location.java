package com.timmy.shoppingreceipt.entity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Location implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    @NotBlank(message = "地點名稱不得為空")
    private String name;
    @NotNull(message = "商品數量不得為空")
    private BigDecimal taxRate;
}

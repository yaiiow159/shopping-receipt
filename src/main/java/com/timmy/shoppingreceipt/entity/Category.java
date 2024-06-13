package com.timmy.shoppingreceipt.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    @NotBlank(message = "分類名稱不得為空")
    private String name;

    private boolean isTaxExempt;
}

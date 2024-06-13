package com.timmy.shoppingreceipt.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }


    public static BigDecimal rounded(BigDecimal tax) {
        return tax.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal countSubTotal(BigDecimal price, Integer quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public static BigDecimal countTotal(BigDecimal subTotal, BigDecimal tax) {
        return subTotal.add(tax);
    }
}

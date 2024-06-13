package com.timmy.shoppingreceipt;


import com.timmy.shoppingreceipt.util.BigDecimalUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigDecimalUtilTest {

    @Test
    public void test_roundUp() {
        BigDecimal tax = new BigDecimal("1.03");
        BigDecimal tax1 = new BigDecimal("1.12");
        BigDecimal tax2 = new BigDecimal("2.23");

        BigDecimal roundedTax = BigDecimalUtil.roundedUp(tax);
        BigDecimal roundedTax1 = BigDecimalUtil.roundedUp(tax1);
        BigDecimal roundedTax2 = BigDecimalUtil.roundedUp(tax2);

        assertEquals(new BigDecimal("1.05"), roundedTax);
        assertEquals(new BigDecimal("1.15"), roundedTax1);
        assertEquals(new BigDecimal("2.25"), roundedTax2);
    }

    @Test
    public void test_countSubTotal() {
        BigDecimal price = new BigDecimal("100");
        Integer quantity = 21;
        BigDecimal subTotal = BigDecimalUtil.countSubTotal(price, quantity);
        assertEquals(new BigDecimal("2100"), subTotal);
    }

    @Test
    public void test_countTotal() {
        BigDecimal subTotal = new BigDecimal("2100");
        BigDecimal tax = new BigDecimal("100");
        BigDecimal total = BigDecimalUtil.countTotal(subTotal, tax);
        assertEquals(new BigDecimal("2200"), total);
    }
}

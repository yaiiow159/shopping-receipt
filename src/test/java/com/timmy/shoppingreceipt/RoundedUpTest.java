package com.timmy.shoppingreceipt;


import com.timmy.shoppingreceipt.util.BigDecimalUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundedUpTest {

    /**
     * 確認 四捨五入 正確 必且能正確改變為0.05倍數
     */
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

        System.out.println("roundedTax = " + roundedTax);
        System.out.println("roundedTax1 = " + roundedTax1);
        System.out.println("roundedTax2 = " + roundedTax2);
    }
}

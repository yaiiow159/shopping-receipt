package com.timmy.shoppingreceipt;


import com.timmy.shoppingreceipt.entity.Category;
import com.timmy.shoppingreceipt.entity.Location;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityTest {
    @Test
    public void test_location() {
        Location location = new Location();
        location.setId(1);
        location.setName("test");
        location.setTaxRate(BigDecimal.valueOf(0.1));

        assertEquals(1, location.getId());
        assertEquals("test", location.getName());
        assertEquals(BigDecimal.valueOf(0.1), location.getTaxRate());
    }

    @Test
    public void test_product() {
        Location location = new Location();
        location.setId(1);
        location.setName("test");
        location.setTaxRate(BigDecimal.valueOf(0.1));

        assertEquals(1, location.getId());
        assertEquals("test", location.getName());
        assertEquals(BigDecimal.valueOf(0.1), location.getTaxRate());
    }
    @Test
    public void test_category() {
        Category category = new Category();
        category.setId(1);
        category.setName("test");
        category.setTaxExempt(true);

        assertEquals(1, category.getId());
        assertEquals("test", category.getName());
        assertEquals(true, category.isTaxExempt());
    }


}

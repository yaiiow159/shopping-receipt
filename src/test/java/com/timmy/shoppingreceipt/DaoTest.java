package com.timmy.shoppingreceipt;

import com.timmy.shoppingreceipt.dao.CategoryDao;
import com.timmy.shoppingreceipt.dao.LocationDao;
import com.timmy.shoppingreceipt.dao.ProductDao;
import com.timmy.shoppingreceipt.entity.Category;
import com.timmy.shoppingreceipt.entity.Location;
import com.timmy.shoppingreceipt.entity.Product;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DaoTest {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private ProductDao productDao;

    @Test
    public void test_getEntityById() {
        Location location = locationDao.getLocationById(1);
        Category category = categoryDao.getCategoryById(1);
        Product product = productDao.getProductById(1);
        assertEquals("CALIFORNIA", location.getName());
        assertEquals("FOOD", category.getName());
        assertEquals("APPLE", product.getName());
    }

    @Test
    public void test_getAllEntity() {
        List<Location> locations = locationDao.getAllLocations();
        List<Category> categories = categoryDao.getAllCategories();
        List<Product> products = productDao.getAllProducts();
        assertNotNull(locations);
        assertNotNull(categories);
        assertNotNull(products);
    }
}

package com.timmy.shoppingreceipt;

import com.timmy.shoppingreceipt.dao.CategoryDao;
import com.timmy.shoppingreceipt.dao.LocationDao;
import com.timmy.shoppingreceipt.dao.ProductDao;
import com.timmy.shoppingreceipt.dao.ShoppingReceiptDao;
import com.timmy.shoppingreceipt.entity.Category;
import com.timmy.shoppingreceipt.entity.Location;
import com.timmy.shoppingreceipt.entity.Product;
import com.timmy.shoppingreceipt.service.ShoppingService;

import com.timmy.shoppingreceipt.vo.BasicOutput;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static com.timmy.shoppingreceipt.util.BigDecimalUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ServiceTest {

    @MockBean
    private ShoppingService shoppingService;

    @MockBean
    private ShoppingReceiptDao shoppingReceiptDao;

    @MockBean
    private LocationDao locationDao;

    @MockBean
    private ProductDao productDao;

    @MockBean
    private CategoryDao categoryDao;

    @Test
    public void test_calculateAndPrint_when_location_is_not_null() {
        BasicOutput basicOutput = new BasicOutput();
        basicOutput.setTaxRate(BigDecimal.valueOf(0.1));
        basicOutput.setPrice(BigDecimal.valueOf(100));
        basicOutput.setQuantity(1);
        basicOutput.setTax(BigDecimal.ZERO);
        basicOutput.setSubtotal(countSubTotal(basicOutput.getPrice(), basicOutput.getQuantity()));
        basicOutput.setTotal(countTotal(basicOutput.getSubtotal(), basicOutput.getTax()));

        Product product = new Product();
        product.setLocationId(1);
        Location location = new Location();
        location.setId(1);
        location.setTaxRate(BigDecimal.valueOf(0.1));
        Mockito.when(locationDao.getLocationById(product.getLocationId())).thenReturn(location);
        Mockito.when(productDao.checkTaxIsZero(any(Product.class))).thenReturn(true);
        Mockito.when(shoppingService.calculateAndPrint(any(Product.class))).thenReturn(List.of(basicOutput));

        assertEquals(basicOutput.getTax(), shoppingService.calculateAndPrint(product).get(0).getTax());
        assertEquals(basicOutput.getSubtotal(), shoppingService.calculateAndPrint(product).get(0).getSubtotal());
        assertEquals(basicOutput.getTotal(), shoppingService.calculateAndPrint(product).get(0).getTotal());
    }

    @Test
    public void test_calculateAndPrint_when_location_is_null() {
        BasicOutput basicOutput = new BasicOutput();
        basicOutput.setTaxRate(BigDecimal.valueOf(0.1));
        basicOutput.setPrice(BigDecimal.valueOf(100));
        basicOutput.setQuantity(1);
        basicOutput.setTax(basicOutput.getPrice().multiply(basicOutput.getTaxRate()).multiply(BigDecimal.valueOf(basicOutput.getQuantity())));
        basicOutput.setSubtotal(countSubTotal(basicOutput.getPrice(), basicOutput.getQuantity()));
        basicOutput.setTotal(countTotal(basicOutput.getSubtotal(), basicOutput.getTax()));

        Product product = new Product();
        product.setQuantity(1);
        product.setLocationId(0);

        Mockito.when(locationDao.getLocationById(product.getLocationId())).thenReturn(null);
        Mockito.when(shoppingReceiptDao.calculateAndPrint(any(Product.class))).thenReturn(List.of(basicOutput));
        Mockito.when(productDao.checkTaxIsZero(any(Product.class))).thenReturn(false);
        Mockito.when(shoppingService.calculateAndPrint(any(Product.class))).thenReturn(List.of(basicOutput));

        assertEquals(basicOutput.getTax(), shoppingService.calculateAndPrint(product).get(0).getTax());
        assertEquals(basicOutput.getSubtotal(), shoppingService.calculateAndPrint(product).get(0).getSubtotal());
        assertEquals(basicOutput.getTotal(), shoppingService.calculateAndPrint(product).get(0).getTotal());
    }

    @Test
    public void test_getAllLocations() {
        List<Location> locations = List.of(new Location());
        Mockito.when(locationDao.getAllLocations()).thenReturn(locations);
        Mockito.when(shoppingService.getAllLocations()).thenReturn(locations);
        assertEquals(locations, shoppingService.getAllLocations());
    }

    @Test
    public void test_getAllCategories() {
        List<Category> categories = List.of(new Category());
        Mockito.when(categoryDao.getAllCategories()).thenReturn(categories);
        Mockito.when(shoppingService.getAllCategories()).thenReturn(categories);
        assertEquals(categories, shoppingService.getAllCategories());
    }

    @Test
    public void test_getAllProducts() {
        List<Product> products = List.of(new Product());
        Mockito.when(productDao.getAllProducts()).thenReturn(products);
        Mockito.when(shoppingService.getAllProducts()).thenReturn(products);
        assertEquals(products, shoppingService.getAllProducts());
    }

    @Test
    public void test_getAllProductsByCategoryId() {
        int categoryId = 1;
        List<Product> products = List.of(new Product());
        Mockito.when(productDao.getAllProductsByCategoryId(categoryId)).thenReturn(products);
        Mockito.when(shoppingService.getAllProductsByCategoryId(categoryId)).thenReturn(products);
        assertEquals(products, shoppingService.getAllProductsByCategoryId(categoryId));
    }

    @Test
    public void test_getAllProductsByCategoryIdAndLocationId() {
        int categoryId = 1;
        int locationId = 1;
        List<Product> products = List.of(new Product());
        Mockito.when(productDao.getAllProductsByCategoryIdAndLocationId(categoryId, locationId)).thenReturn(products);
        Mockito.when(shoppingService.getAllProductsByCategoryIdAndLocationId(categoryId, locationId)).thenReturn(products);
        assertEquals(products, shoppingService.getAllProductsByCategoryIdAndLocationId(categoryId, locationId));
    }
}

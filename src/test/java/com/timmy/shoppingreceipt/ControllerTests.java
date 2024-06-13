package com.timmy.shoppingreceipt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timmy.shoppingreceipt.controller.ShoppingController;
import com.timmy.shoppingreceipt.entity.Category;
import com.timmy.shoppingreceipt.entity.Location;
import com.timmy.shoppingreceipt.entity.Product;
import com.timmy.shoppingreceipt.service.ShoppingService;
import com.timmy.shoppingreceipt.vo.BasicOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ShoppingService shoppingService;
    @Test
    void test_ToIndex() throws Exception {
        List<Location> allLocations = List.of(new Location());
        List<Category> allCategories = List.of(new Category());
        List<Product> allProducts = List.of(new Product());
        when(shoppingService.getAllLocations()).thenReturn(allLocations);
        when(shoppingService.getAllCategories()).thenReturn(allCategories);
        when(shoppingService.getAllProducts()).thenReturn(allProducts);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("locations", allLocations))
                .andExpect(model().attribute("categories", allCategories))
                .andExpect(model().attribute("products", allProducts));
    }

    @Test
    void test_getProducts() throws Exception {
        Integer categoryId = 1;
        Integer locationId = 1;
        List<Product> allProducts = List.of(new Product());
        String json = objectMapper.writeValueAsString(allProducts);
        when(shoppingService.getAllProductsByCategoryIdAndLocationId(
                categoryId, locationId))
                .thenReturn(allProducts);
        mockMvc.perform(
                get("/getProducts")
                        .param("categoryId", "1")
                        .param("locationId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(json)).andReturn();
    }

    @Test
    void test_receipt() throws Exception {
        List<BasicOutput> productPriceTaxs = List.of(new BasicOutput());
        String json = objectMapper.writeValueAsString(productPriceTaxs);
        String postBody = "{\"locationId\":1,\"name\":\"test\",\"price\":100,\"quantity\":1}";
        when(shoppingService.calculateAndPrint(any(Product.class))).thenReturn(productPriceTaxs);
        mockMvc.perform(post("/receipt")
                        .content(postBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(json)).andReturn();
    }


}

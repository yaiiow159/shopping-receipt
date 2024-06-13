package com.timmy.shoppingreceipt.controller;

import com.timmy.shoppingreceipt.entity.Category;
import com.timmy.shoppingreceipt.entity.Location;
import com.timmy.shoppingreceipt.entity.Product;
import com.timmy.shoppingreceipt.service.ShoppingService;
import com.timmy.shoppingreceipt.vo.BasicOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShoppingController {

    private final ShoppingService shoppingService;

    @GetMapping("/")
    public ModelAndView toIndex() {
        List<Location> allLocations = shoppingService.getAllLocations();
        List<Category> allCategories = shoppingService.getAllCategories();
        List<Product> allProducts = shoppingService.getAllProducts();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("locations", allLocations);
        modelAndView.addObject("categories", allCategories);
        modelAndView.addObject("products", allProducts);
        return modelAndView;
    }

    @GetMapping("/getProducts")
    @ResponseBody
    public List<Product> getProducts(Integer categoryId, Integer locationId) {
        return shoppingService.getAllProductsByCategoryIdAndLocationId(categoryId, locationId);
    }

    @PostMapping("/receipt")
    @ResponseBody
    public List<BasicOutput> receipt(@Valid @RequestBody Product product) {
        return shoppingService.calculateAndPrint(product);
    }
}

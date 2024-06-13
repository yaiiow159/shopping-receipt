package com.timmy.shoppingreceipt.service;

import com.timmy.shoppingreceipt.entity.Category;
import com.timmy.shoppingreceipt.entity.Location;
import com.timmy.shoppingreceipt.entity.Product;
import com.timmy.shoppingreceipt.vo.BasicOutput;

import java.util.List;

public interface ShoppingService {
    public List<BasicOutput> calculateAndPrint(Product product);

    public List<Location> getAllLocations();

    public List<Category> getAllCategories();

    public List<Product> getAllProductsByCategoryId(int categoryId);

    List<Product> getAllProducts();

    List<Product> getAllProductsByCategoryIdAndLocationId(Integer categoryId, Integer locationId);
}

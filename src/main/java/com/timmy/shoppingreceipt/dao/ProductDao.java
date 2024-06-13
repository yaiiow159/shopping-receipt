package com.timmy.shoppingreceipt.dao;

import com.timmy.shoppingreceipt.entity.Product;
import com.timmy.shoppingreceipt.vo.BasicOutput;

import java.util.List;

public interface ProductDao {
    public List<Product> getAllProductsByCategoryId(int categoryId);

    boolean checkTaxIsZero(BasicOutput basicOutput);

    boolean checkTaxIsZero(Product product);

    List<Product> getAllProducts();

    List<Product> getAllProductsByCategoryIdAndLocationId(Integer categoryId, Integer locationId);

    Product getProductById(Integer i);
}

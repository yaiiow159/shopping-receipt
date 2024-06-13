package com.timmy.shoppingreceipt.dao;

import com.timmy.shoppingreceipt.entity.Category;
import com.timmy.shoppingreceipt.vo.BasicOutput;

import java.util.List;

public interface CategoryDao {
    public List<Category> getAllCategories();

    public Category getCategoryById(Integer i);
}

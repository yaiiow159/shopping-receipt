package com.timmy.shoppingreceipt.dao;

import com.timmy.shoppingreceipt.entity.Category;
import com.timmy.shoppingreceipt.entity.Location;
import com.timmy.shoppingreceipt.entity.Product;
import com.timmy.shoppingreceipt.vo.BasicOutput;

import java.util.List;

public interface ShoppingReceiptDao {
    public List<BasicOutput> calculateAndPrint(Product product);
}

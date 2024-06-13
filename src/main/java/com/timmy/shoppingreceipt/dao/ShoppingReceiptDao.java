package com.timmy.shoppingreceipt.dao;

import com.timmy.shoppingreceipt.vo.BasicOutput;

import java.util.List;

public interface ShoppingReceiptDao {
    public List<BasicOutput> calculateAndPrintAll();
}

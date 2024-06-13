package com.timmy.shoppingreceipt.service.serviceimpl;

import com.timmy.shoppingreceipt.dao.CategoryDao;
import com.timmy.shoppingreceipt.dao.LocationDao;
import com.timmy.shoppingreceipt.dao.ProductDao;
import com.timmy.shoppingreceipt.dao.ShoppingReceiptDao;
import com.timmy.shoppingreceipt.entity.Category;
import com.timmy.shoppingreceipt.entity.Location;
import com.timmy.shoppingreceipt.entity.Product;
import com.timmy.shoppingreceipt.service.ShoppingService;
import com.timmy.shoppingreceipt.vo.BasicOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.timmy.shoppingreceipt.util.BigDecimalUtil.*;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ShoppingServiceImpl implements ShoppingService {

    private final ShoppingReceiptDao shoppingReceiptDao;
    private final LocationDao locationDao;
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Override
    public List<BasicOutput> calculateAndPrint(Product product) {
        Location location = locationDao.getLocationById(product.getLocationId());
        if(ObjectUtils.isEmpty(location)) {
            List<BasicOutput> basicOutputs = shoppingReceiptDao.calculateAndPrintAll();
            for (BasicOutput basicOutput: basicOutputs) {
                createInfo(product, basicOutput);
            }
            return basicOutputs;
        }
        BasicOutput basicOutput = new BasicOutput();
        basicOutput.setProductName(product.getName());
        basicOutput.setLocationName(location.getName());
        basicOutput.setTaxRate(location.getTaxRate());
        createInfo(product, location, basicOutput);
        return List.of(basicOutput);
    }

    private void createInfo(Product product, BasicOutput basicOutput) {
        if(!productDao.checkTaxIsZero(basicOutput)) {
            basicOutput.setTax(BigDecimal.ZERO);
        } else {
            basicOutput.setTax(rounded(
                    basicOutput.getTaxRate()
                            .multiply(product.getPrice())
                            .multiply(BigDecimal.valueOf(product.getQuantity()))));
        }
        basicOutput.setPrice(product.getPrice());
        basicOutput.setQuantity(product.getQuantity());
        basicOutput.setSubtotal(countSubTotal(product.getPrice(), product.getQuantity()));
        basicOutput.setTotal(countTotal(basicOutput.getSubtotal(), basicOutput.getTax()));
    }

    private void createInfo(Product product, Location location, BasicOutput basicOutput) {
        if(!productDao.checkTaxIsZero(basicOutput)) {
            basicOutput.setTax(BigDecimal.ZERO);
        } else {
            basicOutput.setTax(rounded(
                    location.getTaxRate()
                            .multiply(product.getPrice())
                            .multiply(BigDecimal.valueOf(product.getQuantity()))));
        }
        basicOutput.setPrice(product.getPrice());
        basicOutput.setTaxRate(location.getTaxRate());
        basicOutput.setQuantity(product.getQuantity());
        basicOutput.setSubtotal(countSubTotal(product.getPrice(), product.getQuantity()));
        basicOutput.setTotal(countTotal(basicOutput.getSubtotal(), basicOutput.getTax()));
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public List<Product> getAllProductsByCategoryId(int categoryId) {
        return productDao.getAllProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public List<Product> getAllProductsByCategoryIdAndLocationId(Integer categoryId, Integer locationId) {
        return productDao.getAllProductsByCategoryIdAndLocationId(categoryId, locationId);
    }
}

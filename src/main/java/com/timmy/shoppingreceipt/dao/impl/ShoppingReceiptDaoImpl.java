package com.timmy.shoppingreceipt.dao.impl;

import com.timmy.shoppingreceipt.dao.ShoppingReceiptDao;
import com.timmy.shoppingreceipt.entity.Category;
import com.timmy.shoppingreceipt.entity.Location;
import com.timmy.shoppingreceipt.entity.Product;
import com.timmy.shoppingreceipt.vo.BasicOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShoppingReceiptDaoImpl implements ShoppingReceiptDao {

    private final JdbcTemplate jdbcTemplate;

    public static final class BasicOutputRowMapper implements RowMapper<BasicOutput> {
        @Override
        public BasicOutput mapRow(ResultSet rs, int rowNum) throws SQLException {
            BasicOutput basicOutput = new BasicOutput();
            basicOutput.setProductName(rs.getString("productName"));
            basicOutput.setCategoryName(rs.getString("categoryName"));
            basicOutput.setLocationName(rs.getString("locationName"));
            basicOutput.setTaxRate(rs.getBigDecimal("tax_rate"));
            basicOutput.setQuantity(rs.getInt("quantity"));
            basicOutput.setPrice(rs.getBigDecimal("price"));
            return basicOutput;
        }
    }
    @Override
    public List<BasicOutput> calculateAndPrint(Product product) {
        String sql = "SELECT product.name AS productName,category.name AS categoryName,location.name AS locationName, tax_rate, quantity, price FROM shopping_receipt.product " +
                "INNER JOIN shopping_receipt.location ON product.location_id = location.id " +
                "INNER JOIN shopping_receipt.category ON product.category_id = category.id ";
        return jdbcTemplate.query(sql, new BasicOutputRowMapper());
    }

}

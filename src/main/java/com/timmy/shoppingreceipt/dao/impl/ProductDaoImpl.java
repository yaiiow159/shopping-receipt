package com.timmy.shoppingreceipt.dao.impl;

import com.timmy.shoppingreceipt.dao.ProductDao;
import com.timmy.shoppingreceipt.entity.Product;
import com.timmy.shoppingreceipt.vo.BasicOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {
    private final JdbcTemplate jdbcTemplate;
    public static final class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setLocationId(rs.getInt("location_id"));
            product.setCategoryId(rs.getInt("category_id"));
            product.setTaxExempt(rs.getBoolean("is_tax_exempt"));
            return product;
        }
    }
    @Override
    public List<Product> getAllProductsByCategoryId(int categoryId) {
        String sql = "SELECT * FROM product WHERE category_id = ?";
        return jdbcTemplate.query(sql, new ProductRowMapper(), categoryId);
    }

    @Override
    public boolean checkTaxIsZero(BasicOutput basicOutput) {
        String sql =  "SELECT COUNT(*) FROM shopping_receipt.product " +
                "INNER JOIN shopping_receipt.category " +
                "ON category.id = product.category_id " +
                "WHERE (category.is_tax_exempt = true OR product.is_tax_exempt = true)" +
                "AND product.name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{basicOutput.getProductName()}, Integer.class);
        return count == 0;
    }
    
    @Override
    public boolean checkTaxIsZero(Product product) {
        String sql = "SELECT COUNT(*) FROM shopping_receipt.product " +
                "INNER JOIN shopping_receipt.category " +
                "ON category.id = product.category_id " +
                "WHERE (category.is_tax_exempt = true OR product.is_tax_exempt = true) " +
                "AND product.name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{product.getName()}, Integer.class);
        return count == 0;
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public List<Product> getAllProductsByCategoryIdAndLocationId(Integer categoryId, Integer locationId) {
        String sql = "SELECT * FROM product WHERE (category_id = ? AND location_id = ?)";
        return jdbcTemplate.query(sql, new ProductRowMapper(), categoryId, locationId);
    }

    @Override
    public Product getProductById(Integer id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

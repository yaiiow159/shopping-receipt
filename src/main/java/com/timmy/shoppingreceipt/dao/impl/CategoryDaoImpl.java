package com.timmy.shoppingreceipt.dao.impl;

import com.timmy.shoppingreceipt.dao.CategoryDao;
import com.timmy.shoppingreceipt.entity.Category;
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
public class CategoryDaoImpl implements CategoryDao {
    private final JdbcTemplate jdbcTemplate;
    public static final class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setTaxExempt(rs.getBoolean("is_tax_exempt"));
            return category;
        }
    }
    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM shopping_receipt.category";
        return jdbcTemplate.query(sql, new CategoryRowMapper());
    }

    @Override
    public Category getCategoryById(Integer id) {
        String sql = "SELECT * FROM shopping_receipt.category WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CategoryRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

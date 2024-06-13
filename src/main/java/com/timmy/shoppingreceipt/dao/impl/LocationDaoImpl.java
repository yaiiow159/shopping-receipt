package com.timmy.shoppingreceipt.dao.impl;

import com.timmy.shoppingreceipt.dao.LocationDao;
import com.timmy.shoppingreceipt.entity.Location;
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
public class LocationDaoImpl implements LocationDao {
    private final JdbcTemplate jdbcTemplate;
    public static final class LocationRowMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("id"));
            location.setName(rs.getString("name"));
            location.setTaxRate(rs.getBigDecimal("tax_rate"));
            return location;
        }
    }
    @Override
    public List<Location> getAllLocations() {
        String sql = "SELECT * FROM shopping_receipt.location";
        return jdbcTemplate.query(sql, new LocationRowMapper());
    }

    @Override
    public Location getLocationById(int locationId) {
        String sql = "SELECT * FROM shopping_receipt.location WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new LocationRowMapper(), locationId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

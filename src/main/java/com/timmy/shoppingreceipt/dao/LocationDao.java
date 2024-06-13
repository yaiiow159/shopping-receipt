package com.timmy.shoppingreceipt.dao;

import com.timmy.shoppingreceipt.entity.Location;

import java.util.List;

public interface LocationDao {

    public List<Location> getAllLocations();

    public Location getLocationById(int locationId);
}

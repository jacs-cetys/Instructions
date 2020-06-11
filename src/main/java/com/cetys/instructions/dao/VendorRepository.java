package com.cetys.instructions.dao;
/*****
 *   Created by Jose Armando Cardenas
 *   on 06/06/2020
 */

import com.cetys.instructions.model.Vendor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository
public interface VendorRepository extends CrudRepository<Vendor, Long>
{
    // Find by - Status - Order by Name
    List<Vendor> findAllByStatusOrderByNameAsc(Boolean b);

    // Find Top by - Software
    Vendor findTopBySoftwareEquals(String s);

}

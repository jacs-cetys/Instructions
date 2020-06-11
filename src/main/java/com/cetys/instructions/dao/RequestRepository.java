package com.cetys.instructions.dao;
/*****
 *   Created by Jose Armando Cardenas
 *   on 07/06/2020
 */

import com.cetys.instructions.model.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {

    // Find - by Server.Alias Contains - Order By
    List<Request> findByVendorServerAliasContainsOrderByReqidDesc(String s1);

    // Find - by Employee
    List<Request> findByEmployeeLastnameContainsOrderByEmployeeLastnameAscEmployeeFirstnameAsc(String s);

    // Find - by Date After - OrderBy Regid Desc
    List<Request> findByReqdateAfterOrderByReqidDesc(String s);

    // Find - by Vendor or Software
    List<Request> findByVendorNameContainsOrderByVendorNameAscVendorSoftwareAsc(String s);

    // Find - by Vendor or Software
    List<Request> findByVendorSoftwareContainsOrderByVendorNameAscVendorSoftwareAsc(String s);

}

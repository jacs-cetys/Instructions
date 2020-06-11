package com.cetys.instructions.dao;
/*****
 *   Created by Jose Armando Cardenas
 *   on 01/06/2020
 */

import com.cetys.instructions.model.Server;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ServerRepository extends CrudRepository<Server, Long> {

    // find - by Status -  OrderBy Hostname Asc
    List<Server> findByStatusEqualsOrderByHostnameAsc(Boolean b);

    // find - by Hostname
    Server findTop1ByHostnameEquals(String s);

}

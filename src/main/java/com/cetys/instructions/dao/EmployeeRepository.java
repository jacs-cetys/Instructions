package com.cetys.instructions.dao;
/*****
 *   Created by Jose Armando Cardenas
 *   on 01/06/2020
 */

import com.cetys.instructions.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>
{
    // find - by Status -  OrderBy [Lastname, Firstname] Asc
    List<Employee> findByStatusEqualsOrderByLastnameAscFirstnameAsc(String s);

    // find Top 1 - by Email Equals
    Employee findTop1ByEmailEquals(String s);

    /*
    // find - by Lastname - OrderBy [Lastname, Firstname] Asc
    List<Employee> find


    // find - by Email - OrderBy Email Asc


    // find - by Country - OrderBy Country
    */

}

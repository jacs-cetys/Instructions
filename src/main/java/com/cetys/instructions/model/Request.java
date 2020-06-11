package com.cetys.instructions.model;
/*****
 *   Created by Jose Armando Cardenas
 *   on 01/06/2020
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reqid;

    private String justification;

    private String reqdate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vendor vendor;

    public Request(String justification)
    {
        this.justification = justification;
        this.reqdate = this.getReqDateUpdated();
    }

    public Request(String justification, Employee employee, Vendor vendor)
    {
        this.justification = justification;
        this.employee = employee;
        this.vendor = vendor;
        this.reqdate = this.getReqDateUpdated();
    }

    public Request(String justification, Employee employee, Vendor vendor, String reqdate)
    {
        this.justification = justification;
        this.employee = employee;
        this.vendor = vendor;
        this.reqdate = reqdate;
    }

    public String getReqDateUpdated()
    {
        LocalDate now = LocalDate.now();

        return now.toString();
    }

    public Vendor getVendor()
    {
        if(this.vendor == null)
        {
            this.vendor = new Vendor();
        }

        return this.vendor;
    }

    public Employee getEmployee()
    {
        if(this.employee == null)
        {
            this.employee = new Employee();
        }

        return this.employee;
    }

}

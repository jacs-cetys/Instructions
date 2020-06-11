package com.cetys.instructions.model;
/*****
 *   Created by Jose Armando Cardenas
 *   on 01/06/2020
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empid;

    private String firstname;

    private String lastname;

    private String email;

    private String sbg;

    private String country;

    private String status;

    // @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    private List<Request> requests = new ArrayList<>();
    // @OneToMany(mappedBy = "employee")
    // private List<Request> requests;

    /* public Employee()
    {
        this.requests = new ArrayList<Request>();
    } */

    public Employee(String firstname, String lastname, String email, String sbg, String country, String status)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.sbg = sbg;
        this.country = country;
        this.status = status;
    }

    public String getName()
    {
        String name = this.lastname + ", " + this.firstname;
        return name;
    }

}

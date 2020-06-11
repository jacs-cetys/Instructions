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
public class Server
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serverid;

    private String hostname;

    private String opersys;

    private String domain;

    private String alias;

    private Boolean status;

    @OneToMany(mappedBy = "server", orphanRemoval = true)
    private List<Vendor> vendors = new ArrayList<>();
    /* @OneToMany(mappedBy = "server")
    private List<Vendor> vendors;

    public Server()
    {
        this.vendors  = new ArrayList<Vendor>();
    } */

    public Server(String hostname, String opersys, String domain, String alias, Boolean status)
    {
        this.hostname = hostname;
        this.opersys = opersys;
        this.domain = domain;
        this.alias = alias;
        this.status = status;
    }

    public String getStrStatus()
    {
        if(this.status)
            return "ACTIVE";
        else
            return "DISABLED";
    }
}

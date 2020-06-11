package com.cetys.instructions.model;
/*****
 *   Created by Jose Armando Cardenas
 *   on 01/06/2020
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Vendor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorid;

    private String name;

    private String software;

    private Integer port;

    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Server server;

    @OneToMany(mappedBy = "vendor", orphanRemoval = true)
    private List<Request> requests = new ArrayList<>();
    /* @OneToMany(mappedBy = "vendor")
    private List<Request> requests;

    public Vendor()
    {
        this.requests = new ArrayList<Request>();
    } */

    public Vendor(String name, String software, Integer port, Server server, Boolean status)
    {
        this.name = name;
        this.software = software;
        this.port = port;
        this.status = status;
        this.server = server;
    }

    public String getStrStatus()
    {
        if(this.status)
            return "ACTIVE";
        else
            return "DISABLED";
    }

    public Server getServer()
    {
        if(this.server == null)
        {
            this.server = new Server();
        }

        return this.server;
    }

    public String getNameSoftware()
    {
        return this.name + "-" + this.software;
    }

}

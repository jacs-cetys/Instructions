package com.cetys.instructions.controller;
/*****
 *   Created by Jose Armando Cardenas
 *   on 01/06/2020
 */

import com.cetys.instructions.dao.ServerRepository;
import com.cetys.instructions.dao.VendorRepository;
import com.cetys.instructions.model.Vendor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vendors")
public class VendorController
{
    final VendorRepository vendorRepository;

    final ServerRepository serverRepository;

    public VendorController(
            VendorRepository vendorRepository
            , ServerRepository serverRepository
    )
    {
        this.serverRepository = serverRepository;
        this.vendorRepository = vendorRepository;
    }

    @Bean("initVendorRepo")
    @DependsOn("initServerRepo")
    public void initVendorRepository()
    {
        vendorRepository.save(new Vendor("Autodesk","Autocad", 27007, serverRepository.findTop1ByHostnameEquals("ca66w1579"), Boolean.TRUE));
        vendorRepository.save(new Vendor("Mathworks","Matlab", 1713, serverRepository.findTop1ByHostnameEquals("ca66w1579"), Boolean.TRUE));
        vendorRepository.save(new Vendor("IBM","Clearcase", 7500, serverRepository.findTop1ByHostnameEquals("de99w4567"), Boolean.TRUE));
        vendorRepository.save(new Vendor("IBM","Clearquest", 7500, serverRepository.findTop1ByHostnameEquals("az14s5412"), Boolean.FALSE));
        vendorRepository.save(new Vendor("Ansys","Fluent", 7241, serverRepository.findTop1ByHostnameEquals("de99w4568"), Boolean.TRUE));
        vendorRepository.save(new Vendor("Siemens","Teamcenter",9807, serverRepository.findTop1ByHostnameEquals("eu78u3311"), Boolean.TRUE));
        vendorRepository.save(new Vendor("Siemens","Ideas", 9812, serverRepository.findTop1ByHostnameEquals("eu78u3311"), Boolean.TRUE));
    }

    /***** READ *****/
    @GetMapping(value = { "", "/" })
    String listVendors(
            Model model
    ){
        model.addAttribute("vendors", vendorRepository.findAll());

        return "view/vendor/vendors-list";
    }

    /***** CREATE *****/
    @GetMapping("/create")
    String createVendor(
            Vendor vendor
            , Model model
    ) {
        model.addAttribute("vendor", vendor);
        model.addAttribute("servers", serverRepository.findByStatusEqualsOrderByHostnameAsc(Boolean.TRUE));

        return "view/vendor/vendor-add";
    }

    @PostMapping("/add")
    ModelAndView addVendor(
            Vendor vendor
            , ModelMap model
    ){
        vendorRepository.save(vendor);
        model.addAttribute("vendors", vendorRepository.findAll());

        return new ModelAndView("redirect:/vendors");
    }

    /***** UPDATE *****/
    @GetMapping("/edit/{id}")
    String editVendor(
            @PathVariable("id") Long id
            , Model model
    ){
        var vendor = vendorRepository.findById(id).get();
        model.addAttribute("vendor", vendor);
        model.addAttribute("servers", serverRepository.findByStatusEqualsOrderByHostnameAsc(Boolean.TRUE));

        return "view/vendor/vendor-edit";
    }

    @PostMapping("/update/{id}")
    ModelAndView updateVendor(
            @PathVariable("id") Long id
            , Vendor editedVendor
            , ModelMap model
    ){
        vendorRepository.save(editedVendor);
        model.addAttribute("vendors", vendorRepository.findAll());

        return new ModelAndView("redirect:/vendors");
    }

    /***** DELETE *****/
    @GetMapping("/delete/{id}")
    ModelAndView deleteVendor(
            @PathVariable("id") Long id
            , ModelMap model
    ){
        vendorRepository.deleteById(id);
        model.addAttribute("vendors", vendorRepository.findAll());

        return new ModelAndView("redirect:/vendors");
    }

}

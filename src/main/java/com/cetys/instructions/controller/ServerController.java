package com.cetys.instructions.controller;
/*****
 *   Created by Jose Armando Cardenas
 *   on 01/06/2020
 */

import com.cetys.instructions.dao.ServerRepository;
import com.cetys.instructions.model.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/servers")
public class ServerController
{
    final ServerRepository serverRepository;

    public ServerController(ServerRepository serverRepository)
    {
        this.serverRepository = serverRepository;
    }

    @Bean("initServerRepo")
    public void initServerRepository()
    {
        serverRepository.save(new Server("de99w4567", "WIN", "global.com", "DESER1", Boolean.TRUE));
        serverRepository.save(new Server("de99w4568", "WIN", "global.com", "DESER2", Boolean.TRUE));
        serverRepository.save(new Server("az14s5412", "SOLARIS", "corp.com", "AZSER1", Boolean.FALSE));
        serverRepository.save(new Server("ca66w1579", "WIN", "corp.com", "CASER1", Boolean.TRUE));
        serverRepository.save(new Server("eu78u3311", "UNIX", "global.com", "EU78U311", Boolean.TRUE));
    }

    /***** READ *****/
    @GetMapping(value = { "", "/" })
    String listServers(
            Model model
    ){
        model.addAttribute("servers", serverRepository.findAll());

        return "view/server/servers-list";
    }

    /***** CREATE *****/
    @GetMapping("/create")
    String createServer(
            Server server
            , Model model
    ) {
        model.addAttribute("server", server);

        return "view/server/server-add";
    }

    @PostMapping("/add")
    ModelAndView addServer(
            Server server
            , ModelMap model
    ){
        serverRepository.save(server);
        model.addAttribute("servers", serverRepository.findAll());

        return new ModelAndView("redirect:/servers");
    }

    /***** UPDATE *****/
    @GetMapping("/edit/{id}")
    String editServer(
            @PathVariable("id") Long id
            , Model model
    ){
        var server = serverRepository.findById(id).get();
        model.addAttribute("server", server);

        return "view/server/server-edit";
    }

    @PostMapping("/update/{id}")
    ModelAndView updateServer(
            @PathVariable("id") Long id
            , Server editedServer
            , ModelMap model
    ){
        serverRepository.save(editedServer);
        model.addAttribute("servers", serverRepository.findAll());

        return new ModelAndView("redirect:/servers");
    }

    /***** DELETE *****/
    @GetMapping("/delete/{id}")
    ModelAndView deleteServer(
            @PathVariable("id") Long id
            , ModelMap model
    ){
        serverRepository.deleteById(id);
        model.addAttribute("servers", serverRepository.findAll());

        return new ModelAndView("redirect:/servers");
    }

}

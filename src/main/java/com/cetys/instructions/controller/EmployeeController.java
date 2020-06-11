package com.cetys.instructions.controller;
/*****
 *   Created by Jose Armando Cardenas
 *   on 01/06/2020
 */

import com.cetys.instructions.dao.EmployeeRepository;
import com.cetys.instructions.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employees")
public class EmployeeController
{
    final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Bean("initEmployeeRepo")
    public void initEmployeeRepository()
    {
        employeeRepository.save(new Employee("Luis","Sereno", "luis.ser@comp.com", "CORP", "MEX", "Active"));
        employeeRepository.save(new Employee("Luis","Rey", "luis.rey@prof.com", "HBT", "PRI", "Terminated"));
        employeeRepository.save(new Employee("Michael","Clark", "michael.cla@comp.com", "AERO", "USA", "Vacations"));
        employeeRepository.save(new Employee("Sunil","Vamaran", "sunil.vam@corp.com", "CORP", "IND", "Active"));
        employeeRepository.save(new Employee("Martin","Sereno", "martin.ser@comp.com", "PMT", "CHZ", "Active"));
    }

    /***** READ *****/
    @GetMapping(value = { "", "/" })
    String listEmployees(
            Model model
    ){
        model.addAttribute("employees", employeeRepository.findAll());

        return "view/employee/employees-list";
    }

    /***** CREATE *****/
    @GetMapping("/create")
    String createEmployee(
            Employee employee
            , Model model
    ) {
        model.addAttribute("employee", employee);

        return "view/employee/employee-add";
    }

    @PostMapping("/add")
    ModelAndView addEmployee(
            Employee employee
            , ModelMap model
    ){
        employeeRepository.save(employee);
        model.addAttribute("employees", employeeRepository.findAll());

        return new ModelAndView("redirect:/employees");
    }

    /***** UPDATE *****/
    @GetMapping("/edit/{id}")
    String editEmployee(
            @PathVariable("id") Long id
            , Model model
    ){
        var employee = employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);

        return "view/employee/employee-edit";
    }

    @PostMapping("/update/{id}")
    ModelAndView updateEmployee(
            @PathVariable("id") Long id
            , Employee editedEmployee
            , ModelMap model
    ){
        employeeRepository.save(editedEmployee);
        model.addAttribute("employees", employeeRepository.findAll());

        return new ModelAndView("redirect:/employees");
    }

    /***** DELETE *****/
    @GetMapping("/delete/{id}")
    ModelAndView deleteEmployee(
            @PathVariable("id") Long id
            , ModelMap model
    ){
        employeeRepository.deleteById(id);
        model.addAttribute("employees", employeeRepository.findAll());

        return new ModelAndView("redirect:/employees");
    }

}

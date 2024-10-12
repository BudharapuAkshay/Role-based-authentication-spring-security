package com.security.role_based_inmemory.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SecurityController {

    @GetMapping("/employee")
    public String getAllEmployees(){
        return "Success";
    }
    @GetMapping("/employee/{id}")
    public String getEmployeeById(@PathVariable int id){
        return "Success";
    }
    @PostMapping("/employee")
    public String addEmployee(){
        return "Success";
    }
    @PutMapping("/employee")
    public String updateEmployee(){
        return "Success";
    }
    @DeleteMapping("/employee")
    public String deleteEmployee(){
        return "Success";
    }
}

package com.ctd.integrador.backend1.login.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @GetMapping("/")
    public String home() {
        return "<h1>Welcome to the Home Page</h1>";
    }

    @GetMapping("/user")
    public String user() {
        return "<h1>Welcome to the User Page</h1><br><h3>You can't get an appointment by clicking on the link below</h3><br><a href=\"http://localhost:8080/turnos\">Get Appointments</a>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h1>Welcome to the Admin Page</h1>";
    }



}

package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author josesaidolanogarcia
 */
@RestController
public class UsuarioController {

    @GetMapping("/")
    public String home(){
        return "Bienvenidos...";
    }

    @GetMapping("/user")
    public String user(){
        return "Menu Usuarios";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Menu Administradores";
    }

    @GetMapping("/customer")
    public String superadmin(){
        return "Menu Customers";
    }

}

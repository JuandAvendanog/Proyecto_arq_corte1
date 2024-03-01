package com.proyectocorte1.Proyecto_arquitectura_corte1.controllers;


import com.proyectocorte1.Proyecto_arquitectura_corte1.model.User;
import com.proyectocorte1.Proyecto_arquitectura_corte1.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/usuario")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/usuario")
    public int createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("/usuario/{id}")
    public int updateUser(@PathVariable("id") int id, @RequestBody User updateUser){
        return userService.updateUser(id, updateUser);
    }

    @DeleteMapping("/usuario/{id}")
    public int deleteUser(@PathVariable("id") int id){
        return userService.deleteUser(id);
    }



}

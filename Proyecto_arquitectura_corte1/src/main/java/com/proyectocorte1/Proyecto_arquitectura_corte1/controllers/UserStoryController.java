package com.proyectocorte1.Proyecto_arquitectura_corte1.controllers;

import com.proyectocorte1.Proyecto_arquitectura_corte1.model.UserStory;
import com.proyectocorte1.Proyecto_arquitectura_corte1.services.UserStoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserStoryController {
    private final UserStoryService userStoryService;
    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }
    @GetMapping("/usuario/{id_user}/proyecto/{id_project}/historias")
    public List<UserStory> getAllUserStory(@PathVariable("id_project") int id_project){
        return userStoryService.getAllUserStory(id_project);
    }

    @PostMapping("/usuario/{id_user}/proyecto/{id_project}/historias")
    public String createUserStory(@RequestBody UserStory newUserStory, @PathVariable("id_user") int id_user){
        return userStoryService.createUserStory(newUserStory, id_user);
    }

    @PutMapping("/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}")
    public String updateUserStory(@PathVariable("id_user")  int id_user,
                                @PathVariable("id_project") int id_project,
                                @RequestBody UserStory updateUserStory){
        return userStoryService.updateUserStory(id_user,id_project,updateUserStory);
    }

    @DeleteMapping("/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}")
    public String deleteUserStory(@PathVariable("id_user")  int id_user,
                                  @PathVariable("id_userStory") int id_userStory){
        return userStoryService.deleteUserStory(id_user,id_userStory);
    }
}

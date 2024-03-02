package com.proyectocorte1.Proyecto_arquitectura_corte1.controllers;

import com.proyectocorte1.Proyecto_arquitectura_corte1.model.UserProject;

import com.proyectocorte1.Proyecto_arquitectura_corte1.services.UserProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserProjectController {


    private final UserProjectService userProjectService;

    public UserProjectController(UserProjectService userProjectService) {
        this.userProjectService = userProjectService;
    }

    @GetMapping("/usuario/{id}/proyecto/{id_project}/usuarios")
    public List<UserProject> getAllUserProject(@PathVariable("id_project") int id_project) {
        return userProjectService.getAllUserProject(id_project);
    }

    @PostMapping("/usuario/{id}/proyecto/{id_project}/usuarios")
    public String addUserProject(@PathVariable("id") int id_user,
                                 @PathVariable("id_project") int id_project,
                                 @RequestBody UserProject newUserProject) {
        return userProjectService.addUserProject(id_user, id_project, newUserProject);
    }

    @PutMapping("/usuario/{id}/proyecto/{id_project}/usuarios/{id_user}")
    public String updateUserProject(@PathVariable("id") int id_user,
                                    @PathVariable("id_user") int id_userProject,
                                    @RequestBody UserProject updateUserProject) {
        return userProjectService.updateUserProject(id_user, id_userProject, updateUserProject);
    }

    @DeleteMapping("/usuario/{id}/proyecto/{id_project}/usuarios/{id_user}")
    public String deleteUserProject(@PathVariable("id") int id_user,
                                    @PathVariable("id_user") int id_userProject) {
        return userProjectService.deleteUserProject(id_user, id_userProject);

    }
}

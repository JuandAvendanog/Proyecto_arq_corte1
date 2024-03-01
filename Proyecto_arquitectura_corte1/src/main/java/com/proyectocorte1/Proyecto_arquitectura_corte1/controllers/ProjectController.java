package com.proyectocorte1.Proyecto_arquitectura_corte1.controllers;

import com.proyectocorte1.Proyecto_arquitectura_corte1.model.Project;
import com.proyectocorte1.Proyecto_arquitectura_corte1.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService proyectoServicio) {
        this.projectService = proyectoServicio;
    }

    @GetMapping("/usuario/{id}/proyecto")
    public List<Project> getAllProjects(@PathVariable("id") int id){
        return projectService.getAllProjects(id);
    }

    @PostMapping ("/usuario/{id}/proyecto")
    public String createProject(@RequestBody Project newProject, @PathVariable("id") int id){
        return projectService.createProject(newProject, id);
    }

    @PutMapping("/usuario/{id_user}/proyecto/{id_project}")
    public String updateProject(@PathVariable("id_user") int id_user, @PathVariable("id_project") int id_project, @RequestBody Project updateProject){
        return projectService.updateProject(id_user, id_project, updateProject);
    }

    @DeleteMapping("/usuario/{id_user}/proyecto/{id_project}")
    public String deleteProject(@PathVariable("id_user") int id_user, @PathVariable("id_project") int id_project){
        return projectService.deleteProject(id_user, id_project);
    }


}

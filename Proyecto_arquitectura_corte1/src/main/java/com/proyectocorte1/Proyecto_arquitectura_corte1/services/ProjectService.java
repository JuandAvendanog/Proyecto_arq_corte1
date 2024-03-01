package com.proyectocorte1.Proyecto_arquitectura_corte1.services;

import com.proyectocorte1.Proyecto_arquitectura_corte1.model.Project;
import com.proyectocorte1.Proyecto_arquitectura_corte1.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> getAllProjects(int id) {
        return repository.getAllProjects(id);}

    public String createProject(Project newProject, int id) {
        return repository.createProject(newProject, id);
    }

    public String updateProject(int id_user, int id_project, Project updateProject) {
        return repository.updateProject(id_user, id_project, updateProject);
    }
    public String deleteProject(int id_user, int id_project) {
        return repository.deleteProject(id_user, id_project);
    }


}

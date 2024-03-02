package com.proyectocorte1.Proyecto_arquitectura_corte1.services;

import com.proyectocorte1.Proyecto_arquitectura_corte1.model.UserProject;
import com.proyectocorte1.Proyecto_arquitectura_corte1.repositories.UserProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProjectService {

    private final UserProjectRepository repository;

    public UserProjectService(UserProjectRepository repository) {
        this.repository = repository;
    }


    public List<UserProject> getAllUserProject(int id_project) {
        return repository.getAllUserProject(id_project);
    }

    public String addUserProject(int id_user,int id_project, UserProject newUserProject) {
        return repository.addUserProject(id_user ,id_project, newUserProject);
    }

    public String updateUserProject(int id_user, int id_userProject, UserProject updateUserProejct) {
        return repository.updateUserProject(id_user, id_userProject, updateUserProejct);
    }

    public String deleteUserProject(int id_user, int id_userProject) {
        return repository.deleteUserProject(id_user, id_userProject);
    }
}

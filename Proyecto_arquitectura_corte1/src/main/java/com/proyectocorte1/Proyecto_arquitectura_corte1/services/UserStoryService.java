package com.proyectocorte1.Proyecto_arquitectura_corte1.services;

import com.proyectocorte1.Proyecto_arquitectura_corte1.model.UserProject;
import com.proyectocorte1.Proyecto_arquitectura_corte1.model.UserStory;
import com.proyectocorte1.Proyecto_arquitectura_corte1.repositories.UserStoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStoryService {

    private final UserStoryRepository repository;

    public UserStoryService(UserStoryRepository repository) {
        this.repository = repository;
    }

    public List<UserStory> getAllUserStory(int id_project) {
        return repository.getAllUserStory(id_project);
    }

    public String createUserStory(UserStory newUserStory, int id_user) {
        return repository.createUserStory(newUserStory, id_user);
    }

    public String updateUserStory(int id_user, int id_project, UserStory updateUserStory) {
        return repository.updateUserStory(id_user, id_project, updateUserStory);
    }

    public String deleteUserStory(int id_user, int id_userStory) {
        return repository.deleteUserStory(id_user, id_userStory);
    }

}

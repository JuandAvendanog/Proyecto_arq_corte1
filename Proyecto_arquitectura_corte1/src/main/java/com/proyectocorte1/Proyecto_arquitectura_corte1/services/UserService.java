package com.proyectocorte1.Proyecto_arquitectura_corte1.services;


import com.proyectocorte1.Proyecto_arquitectura_corte1.model.User;
import com.proyectocorte1.Proyecto_arquitectura_corte1.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers(){
        return repository.getAllUsers();
    }


    public int createUser(User newUser) {
        return repository.createUser(newUser);
    }

    public int deleteUser(int id) {
        return repository.deleteUser(id);
    }

    public int updateUser(int id, User updateUser) {
        return repository.updateUser(id, updateUser);
    }
}

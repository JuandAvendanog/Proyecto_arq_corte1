package com.proyectocorte1.Proyecto_arquitectura_corte1.services;

import com.proyectocorte1.Proyecto_arquitectura_corte1.repositories.TaskRepository;
import com.proyectocorte1.Proyecto_arquitectura_corte1.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    public List<Task> getAllTask(int id) {
        return repository.getAllTask(id);
    }

    public String createTask(int id_user, Task newTask) {
        return repository.createTask(id_user, newTask);
    }

    public String updateTask(int id_user, int id_task, Task updateTask) {
        return repository.updateTask(id_user,id_task,updateTask);
    }

    public String deleteTask(int id_user, int id_task) {
        return repository.deleteTask(id_user, id_task);
    }
}

package com.proyectocorte1.Proyecto_arquitectura_corte1.controllers;

import com.proyectocorte1.Proyecto_arquitectura_corte1.services.TaskService;
import com.proyectocorte1.Proyecto_arquitectura_corte1.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}/tareas")
    public List<Task> getAllTask(@PathVariable("id_userStory") int id){
        return taskService.getAllTask(id);
    }

    @PostMapping("/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}/tareas")
    public String createTask(@PathVariable("id_user") int id_user,@RequestBody Task newTask){
        return taskService.createTask(id_user, newTask);
    }

    @PutMapping("/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}/tareas/{id_task}")
    public String updateTask(@PathVariable("id_user") int id_user,
                             @PathVariable("id_task") int id_task,
                             @RequestBody Task updateTask){
        return taskService.updateTask(id_user, id_task, updateTask);
    }

    @DeleteMapping("/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}/tareas/{id_task}")
    public String deleteTask(@PathVariable("id_user") int id_user,
                             @PathVariable("id_task") int id_task){
        return taskService.deleteTask(id_user,id_task);
    }
}

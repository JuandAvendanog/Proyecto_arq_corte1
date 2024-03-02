package com.proyectocorte1.Proyecto_arquitectura_corte1.model;

public class Task {

    public int id;
    public String description;
    public int status;
    public int idUserTask;
    public int idUserStory;

    public Task(int id, String description, int status, int idUserTask, int idUserStory) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.idUserTask = idUserTask;
        this.idUserStory = idUserStory;
    }
}

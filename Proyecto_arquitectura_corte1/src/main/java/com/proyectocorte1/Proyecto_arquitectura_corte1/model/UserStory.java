package com.proyectocorte1.Proyecto_arquitectura_corte1.model;

public class UserStory {

    public int id;

    public String details;

    public String criteria;

    public int idUserStory;

    public int idStatus;

    public int idProject;

    public UserStory(int id, String details, String criteria, int idUserStory, int idStatus, int idProject) {
        this.id = id;
        this.details = details;
        this.criteria = criteria;
        this.idUserStory = idUserStory;
        this.idStatus = idStatus;
        this.idProject = idProject;
    }
}

package com.proyectocorte1.Proyecto_arquitectura_corte1.model;

import java.util.Date;

public class Project {
    public int id;
    public String name;
    public String description;
    public Date startDate;
    public int manager;

    public Project(int id, String name, String description, Date startDate, int manager) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.manager = manager;
    }
}

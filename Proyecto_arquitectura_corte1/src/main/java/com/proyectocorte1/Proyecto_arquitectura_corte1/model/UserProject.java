package com.proyectocorte1.Proyecto_arquitectura_corte1.model;

import java.util.Date;

public class UserProject {
    public int id;
    public Date startDate;
    public int idUser;
    public int idProject;

    public UserProject(int id, Date startDate, int idUser, int idProject) {
        this.id = id;
        this.startDate = startDate;
        this.idUser = idUser;
        this.idProject = idProject;
    }
}

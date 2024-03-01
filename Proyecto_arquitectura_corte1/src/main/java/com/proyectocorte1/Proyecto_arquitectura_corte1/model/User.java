package com.proyectocorte1.Proyecto_arquitectura_corte1.model;

public class User {
    public int id;
    public String name;
    public String mail;
    private String password;
    public int userType;

    public String getPassword() {
        return password;
    }


    public User(int id, String nombre, String correo, String contrasena, int userType) {
        this.id = id;
        this.name = nombre;
        this.mail = correo;
        this.password = contrasena;
        this.userType = userType;
    }
}

package com.jpsoftwares.katana.modelo;

public enum Roles {

    ADMIN("admin"),
    PROFISSIONAL("funcionario");

    private String role;

    Roles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}

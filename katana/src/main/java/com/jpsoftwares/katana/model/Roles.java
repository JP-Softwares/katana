package com.jpsoftwares.katana.model;

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

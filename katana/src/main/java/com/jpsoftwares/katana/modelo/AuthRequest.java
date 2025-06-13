package com.jpsoftwares.katana.modelo;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String senha;
}

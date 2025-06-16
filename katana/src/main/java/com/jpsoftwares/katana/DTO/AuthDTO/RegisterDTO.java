package com.jpsoftwares.katana.DTO.AuthDTO;

import com.jpsoftwares.katana.model.Empresa;
import com.jpsoftwares.katana.model.Roles;

public record RegisterDTO(String nome, String email, String senha, Roles role, String telefone, Empresa empresa) {
}

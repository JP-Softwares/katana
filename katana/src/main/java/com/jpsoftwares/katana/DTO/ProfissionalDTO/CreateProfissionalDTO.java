package com.jpsoftwares.katana.DTO.ProfissionalDTO;

import com.jpsoftwares.katana.model.Empresa;
import com.jpsoftwares.katana.model.Roles;

public record CreateProfissionalDTO(String nome, String email, String senha, Roles role, String telefone, Empresa empresa) {
}

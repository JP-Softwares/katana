package com.jpsoftwares.katana.DTO.ProfissionalDTO;

import com.jpsoftwares.katana.modelo.Roles;

public record CreateProfissionalDTO(String nome, String email, String senha, Roles role, String telefone) {
}

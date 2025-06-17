package com.jpsoftwares.katana.DTO.ProfissionalDTO;

import com.jpsoftwares.katana.model.Roles;

public record ProfissionalCreateDTO(String nome, String email, String senha, Roles role, String telefone, Long empresa) {
}

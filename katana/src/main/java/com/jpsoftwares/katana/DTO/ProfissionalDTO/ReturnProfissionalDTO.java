package com.jpsoftwares.katana.DTO.ProfissionalDTO;

import com.jpsoftwares.katana.model.Roles;

public record ReturnProfissionalDTO(Long id, String nome, String email, Roles role, String tipo) {
}

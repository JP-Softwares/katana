package com.jpsoftwares.katana.DTO.AuthDTO;

import com.jpsoftwares.katana.model.Roles;

public record LoginResponseDTO(String token, Long id,String nome, String email, String tipo, Roles role) {
}

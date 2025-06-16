package com.jpsoftwares.katana.DTO.ClienteDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record ClienteCreateDTO(String nome, String email,String senha) {



}

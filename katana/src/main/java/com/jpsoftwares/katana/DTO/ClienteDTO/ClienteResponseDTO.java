package com.jpsoftwares.katana.DTO.ClienteDTO;


import lombok.Data;

@Data
public record ClienteResponseDTO(Long id, String nome, String email ) {

}

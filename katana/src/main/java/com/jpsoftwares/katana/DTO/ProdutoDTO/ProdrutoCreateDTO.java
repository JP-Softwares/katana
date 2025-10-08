package com.jpsoftwares.katana.DTO.ProdutoDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProdrutoCreateDTO(String nome,
                                String descricao,
                                BigDecimal valor) {
}

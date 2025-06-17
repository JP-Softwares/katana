package com.jpsoftwares.katana.DTO.ProdutoDTO;

import java.math.BigDecimal;

public record ProdrutoCreateDTO(String nome,
                                String descricao,
                                BigDecimal valor) {
}

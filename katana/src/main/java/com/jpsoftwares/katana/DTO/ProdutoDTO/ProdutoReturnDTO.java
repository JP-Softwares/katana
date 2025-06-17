package com.jpsoftwares.katana.DTO.ProdutoDTO;

import java.math.BigDecimal;

public record ProdutoReturnDTO(Long id, String nome, String descricao, BigDecimal valor, boolean ativo) {
}

package com.jpsoftwares.katana.DTO.ServicoDTO;

import java.math.BigDecimal;

public record ServicoReturnDTO(Long id, String nome, String descricao, BigDecimal valor, Boolean ativo) {
}

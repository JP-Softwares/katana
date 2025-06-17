package com.jpsoftwares.katana.DTO.ServicoDTO;

import java.math.BigDecimal;

public record ServicoCreateDTO(String nome, String descricao, BigDecimal valor) {
}

package com.jpsoftwares.katana.DTO.AgendamentoDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AgendamentoCreateDTO(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal, Long cliente_id, Long servico_id, String status) {
}

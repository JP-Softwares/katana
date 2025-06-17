package com.jpsoftwares.katana.DTO.AgendamentoDTO;

import java.time.LocalDateTime;

public record AgendamentoReturnDTO(Long id, LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal,String status, Long cliente_id, Long servico_id) {
}

package com.jpsoftwares.katana.DTO.AgendamentoDTO;

import java.time.LocalDateTime;

public record AgendamentoCreateDTO(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal, Long cliente_id, Long servico_id) {
}

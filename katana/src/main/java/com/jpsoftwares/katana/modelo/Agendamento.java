package com.jpsoftwares.katana.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora_inicial", nullable = false)
    private LocalDateTime dataHoraInicial;

    @Column(name = "data_hora_final", nullable = false)
    private LocalDateTime dataHoraFinal;

    // Relacionamentos (Cliente, Serviço, Profissional) podem ser adicionados aqui

    // Mapeamento Many-to-One para Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Mapeamento Many-to-One para Serviço
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;
}

package com.jpsoftwares.katana.model;

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

    private String status;

    // Relacionamentos (Cliente, Serviço, Profissional) podem ser adicionados aqui

    // Mapeamento Many-to-One para Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;

    // Mapeamento Many-to-One para Serviço
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venda_id", nullable = true)
    private Venda venda;

    public Agendamento(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal,String status, Cliente cliente, Servico servico) {
        this.dataHoraInicial = dataHoraInicial;
        this.dataHoraFinal = dataHoraFinal;
        this.status = status;
        this.cliente = cliente;
        this.servico = servico;
    }
}

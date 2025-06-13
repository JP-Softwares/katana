package com.jpsoftwares.katana.modelo;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "servico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    @Builder.Default
    private Boolean ativo = true;

    // Many-to-One para Profissional
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    // One-to-Many para Serviços
    @OneToMany(
        mappedBy = "servico",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @Builder.Default
    private Set<Agendamento> agendamentos = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}
package com.jpsoftwares.katana.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "empresa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    @Column
    private String nomeFantasia;

    @Column
    private String razaoSocial;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(length = 255)
    private String complemento;

    @Column
    private String CEP;

    @Column
    private String bairro;

    // Mapeamento One-to-Many para Produtos
    @OneToMany(
        mappedBy = "empresa",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @Builder.Default
    private List<Produto> produtos = new ArrayList<>();

    // Mapeamento One-to-Many para Profissionais
    @OneToMany(
        mappedBy = "empresa",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @Builder.Default
    private List<Profissional> profissionais = new ArrayList<>();

    // Mapeamento One-to-Many para Serviços
    @OneToMany(
        mappedBy = "empresa",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @Builder.Default
    private List<Servico> servicos = new ArrayList<>();
}
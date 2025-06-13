package com.jpsoftwares.katana.modelo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cargo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    // Mapeamento One-to-Many para Profissionais
    @OneToMany(
        mappedBy = "cargo",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @Builder.Default
    private List<Profissional> profissionals = new ArrayList<>();

    // Mapeamento Many-to-Many para Permissao
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "cargo_permissao",
        joinColumns = @JoinColumn(name = "cargo_id"),
        inverseJoinColumns = @JoinColumn(name = "permissao_id")
    )
    @Builder.Default
    private Set<Permissao> permissoes = new HashSet<>();
}

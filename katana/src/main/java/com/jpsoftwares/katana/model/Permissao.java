package com.jpsoftwares.katana.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 255)
    private String path;

    // Lado inverso do Many-to-Many
    @ManyToMany(mappedBy = "permissoes")
    @Builder.Default
    private Set<Cargo> cargos = new HashSet<>();
}
package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    // Métodos customizados, se necessários, podem ser adicionados aqui
}

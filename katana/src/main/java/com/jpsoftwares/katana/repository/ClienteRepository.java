package com.jpsoftwares.katana.repository;
import com.jpsoftwares.katana.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Object> findByEmail(String email);
}
package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.modelo.Produto;
import com.jpsoftwares.katana.modelo.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    UserDetails findByEmail(String email);
}

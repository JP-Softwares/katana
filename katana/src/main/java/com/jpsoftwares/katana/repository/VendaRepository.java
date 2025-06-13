package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.modelo.Profissional;
import com.jpsoftwares.katana.modelo.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}

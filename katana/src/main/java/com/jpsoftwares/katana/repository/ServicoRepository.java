package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.modelo.Profissional;
import com.jpsoftwares.katana.modelo.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}

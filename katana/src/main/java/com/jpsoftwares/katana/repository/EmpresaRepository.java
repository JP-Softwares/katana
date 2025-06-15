package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository  extends JpaRepository<Empresa, Long> {
}

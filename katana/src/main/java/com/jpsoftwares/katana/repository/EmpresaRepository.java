package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.modelo.Cliente;
import com.jpsoftwares.katana.modelo.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository  extends JpaRepository<Empresa, Long> {
}

package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.model.Empresa;
import com.jpsoftwares.katana.model.Produto;
import com.jpsoftwares.katana.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    List<Servico> findByEmpresa(Empresa empresa);
}

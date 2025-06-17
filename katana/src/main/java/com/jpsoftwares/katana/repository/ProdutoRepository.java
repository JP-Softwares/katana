package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.model.Empresa;
import com.jpsoftwares.katana.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByEmpresa(Empresa empresa);
}

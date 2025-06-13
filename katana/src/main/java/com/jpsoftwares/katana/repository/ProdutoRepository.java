package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.modelo.Permissao;
import com.jpsoftwares.katana.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

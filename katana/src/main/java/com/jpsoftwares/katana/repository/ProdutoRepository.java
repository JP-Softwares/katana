package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

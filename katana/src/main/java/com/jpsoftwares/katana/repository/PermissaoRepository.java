package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.modelo.ItemVenda;
import com.jpsoftwares.katana.modelo.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}

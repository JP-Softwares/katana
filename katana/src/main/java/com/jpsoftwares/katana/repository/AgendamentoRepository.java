package com.jpsoftwares.katana.repository;

import com.jpsoftwares.katana.model.Agendamento;
import com.jpsoftwares.katana.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByServico_Empresa(Empresa empresa);
}


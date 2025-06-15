package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.model.Agendamento;
import com.jpsoftwares.katana.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    // Método para listar todos os agendamentos
    public List<Agendamento> findAll() {
        return agendamentoRepository.findAll();
    }

    // Método para buscar um agendamento por ID
    public Agendamento findById(Long id) {
        return agendamentoRepository.findById(id)
                .orElse(null);
    }

    // Método para criar um novo agendamento
    public Agendamento create(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    // Método para atualizar um agendamento existente
    public Agendamento update(Agendamento agendamento) {
        if (agendamento.getId() == null || !agendamentoRepository.existsById(agendamento.getId())) {
            return null;
        }
        return agendamentoRepository.save(agendamento);
    }

    // Método para deletar um agendamento por ID
    public boolean delete(Long id) {
        if (!agendamentoRepository.existsById(id)) {
            return false;
        }
        agendamentoRepository.deleteById(id);
        return true;
    }
}


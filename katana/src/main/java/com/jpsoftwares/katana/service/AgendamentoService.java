package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.DTO.AgendamentoDTO.AgendamentoCreateDTO;
import com.jpsoftwares.katana.model.Agendamento;
import com.jpsoftwares.katana.model.Empresa;
import com.jpsoftwares.katana.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Agendamento update(Long id, AgendamentoCreateDTO dto) {
        if (id == null || !agendamentoRepository.existsById(id)) {
            return null;
        }
       Agendamento agenda = agendamentoRepository.findById(id).orElseThrow();

        if(dto.dataHoraFinal() != null){
            agenda.setDataHoraFinal(dto.dataHoraFinal());
        }

        if(dto.dataHoraInicial() != null){
            agenda.setDataHoraInicial(dto.dataHoraInicial());
        }

        if(dto.status() != null){
            agenda.setStatus(dto.status());
        }


        return agendamentoRepository.save(agenda);
    }

    // Método para deletar um agendamento por ID
    public boolean delete(Long id) {
        if (!agendamentoRepository.existsById(id)) {
            return false;
        }

        agendamentoRepository.deleteById(id);
        return true;
    }


    public List<Agendamento> findByServico_Empresa(Empresa empresa) {
        return agendamentoRepository.findByServico_Empresa(empresa);
    }
}


package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.modelo.Servico;
import com.jpsoftwares.katana.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    @Autowired
    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    // Método para listar todos os serviços
    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    // Método para buscar serviço por ID
    public Servico findById(Long id) {
        return servicoRepository.findById(id)
                .orElse(null);
    }

    // Método para criar um novo serviço
    public Servico create(Servico servico) {
        return servicoRepository.save(servico);
    }

    // Método para atualizar um serviço existente
    public Servico update(Servico servico) {
        if (servico.getId() == null || !servicoRepository.existsById(servico.getId())) {
            return null;
        }
        return servicoRepository.save(servico);
    }

    // Método para deletar um serviço por ID
    public boolean delete(Long id) {
        if (!servicoRepository.existsById(id)) {
            return false;
        }
        servicoRepository.deleteById(id);
        return true;
    }
}


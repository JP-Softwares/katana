package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.modelo.Profissional;
import com.jpsoftwares.katana.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    @Autowired
    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    // Método para listar todos os profissionais
    public List<Profissional> findAll() {
        return profissionalRepository.findAll();
    }

    // Método para buscar profissional por ID
    public Profissional findById(Long id) {
        return profissionalRepository.findById(id)
                .orElse(null);
    }

    // Método para criar um novo profissional
    public Profissional create(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    // Método para atualizar um profissional existente
    public Profissional update(Profissional profissional) {
        if (profissional.getId() == null || !profissionalRepository.existsById(profissional.getId())) {
            return null;
        }
        return profissionalRepository.save(profissional);
    }

    // Método para deletar um profissional por ID
    public boolean delete(Long id) {
        if (!profissionalRepository.existsById(id)) {
            return false;
        }
        profissionalRepository.deleteById(id);
        return true;
    }
}

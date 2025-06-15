package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.modelo.Profissional;
import com.jpsoftwares.katana.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    @Autowired
    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }


    public List<Profissional> findAll() {
        return profissionalRepository.findAll();
    }


    public Profissional findById(Long id) {
        return profissionalRepository.findById(id)
                .orElse(null);
    }


    public Profissional create(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }


    public Profissional update(Profissional profissional) {
        if (profissional.getId() == null || !profissionalRepository.existsById(profissional.getId())) {
            return null;
        }
        return profissionalRepository.save(profissional);
    }


    public boolean delete(Long id) {
        if (!profissionalRepository.existsById(id)) {
            return false;
        }
        profissionalRepository.deleteById(id);
        return true;
    }

    public UserDetails findByLogin(Profissional profissional){
        return profissionalRepository.findByEmail(profissional.getEmail());
    }
}

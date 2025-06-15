package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.model.Empresa;
import com.jpsoftwares.katana.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    // Método para listar todas as empresas
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    // Método para buscar empresa por ID
    public Empresa findById(Long id) {
        return empresaRepository.findById(id)
                .orElse(null);
    }

    // Método para criar uma nova empresa
    public Empresa create(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    // Método para atualizar uma empresa existente
    public Empresa update(Empresa empresa) {
        if (empresa.getId() == null || !empresaRepository.existsById(empresa.getId())) {
            return null;
        }
        return empresaRepository.save(empresa);
    }

    // Método para deletar uma empresa por ID
    public boolean delete(Long id) {
        if (!empresaRepository.existsById(id)) {
            return false;
        }
        empresaRepository.deleteById(id);
        return true;
    }
}


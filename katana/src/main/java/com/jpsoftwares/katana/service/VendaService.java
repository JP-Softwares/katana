package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.modelo.Venda;
import com.jpsoftwares.katana.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    // Método para listar todas as vendas
    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    // Método para buscar venda por ID
    public Venda findById(Long id) {
        return vendaRepository.findById(id)
                .orElse(null);
    }

    // Método para criar uma nova venda
    public Venda create(Venda venda) {
        return vendaRepository.save(venda);
    }

    // Método para atualizar uma venda existente
    public Venda update(Venda venda) {
        if (venda.getId() == null || !vendaRepository.existsById(venda.getId())) {
            return null;
        }
        return vendaRepository.save(venda);
    }

    // Método para deletar uma venda por ID
    public boolean delete(Long id) {
        if (!vendaRepository.existsById(id)) {
            return false;
        }
        vendaRepository.deleteById(id);
        return true;
    }
}

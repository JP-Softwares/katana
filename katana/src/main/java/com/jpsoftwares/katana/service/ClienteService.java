package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.modelo.Cliente;
import com.jpsoftwares.katana.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Método para listar todos os clientes
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    // Método para buscar cliente por ID
    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElse(null);
    }

    // Método para criar um novo cliente
    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Método para atualizar um cliente existente
    public Cliente update(Cliente cliente) {
        if (cliente.getId() == null || !clienteRepository.existsById(cliente.getId())) {
            return null;
        }
        return clienteRepository.save(cliente);
    }

    // Método para deletar um cliente por ID
    public boolean delete(Long id) {
        if (!clienteRepository.existsById(id)) {
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }
}

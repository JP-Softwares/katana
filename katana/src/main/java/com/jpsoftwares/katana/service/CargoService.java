package com.jpsoftwares.katana.service;

import com.jpsoftwares.katana.modelo.Cargo;
import com.jpsoftwares.katana.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    // Método para listar todos os cargos
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    // Método para buscar cargo por ID
    public Cargo findById(Long id) {
        return cargoRepository.findById(id)
                .orElse(null);
    }

    // Método para criar um novo cargo
    public Cargo create(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    // Método para atualizar um cargo existente
    public Cargo update(Cargo cargo) {
        if (cargo.getId() == null || !cargoRepository.existsById(cargo.getId())) {
            return null;
        }
        return cargoRepository.save(cargo);
    }

    // Método para deletar um cargo por ID
    public boolean delete(Long id) {
        if (!cargoRepository.existsById(id)) {
            return false;
        }
        cargoRepository.deleteById(id);
        return true;
    }
}

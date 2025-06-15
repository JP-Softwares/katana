package com.jpsoftwares.katana.controller;

import com.jpsoftwares.katana.model.Cargo;
import com.jpsoftwares.katana.service.CargoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Cargo")
@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping
    public ResponseEntity<List<Cargo>> getAll() {
        List<Cargo> cargos = cargoService.findAll();
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getById(@PathVariable Long id) {
        Cargo cargo = cargoService.findById(id);
        if (cargo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cargo);
    }

    @PostMapping
    public ResponseEntity<Cargo> create(@RequestBody Cargo cargo) {
        Cargo created = cargoService.create(cargo);
        return ResponseEntity.ok(created);
    }

    @PutMapping
    public ResponseEntity<Cargo> update(@RequestBody Cargo cargo) {
        Cargo updated = cargoService.update(cargo);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = cargoService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
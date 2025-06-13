package com.jpsoftwares.katana.controller;

import com.jpsoftwares.katana.modelo.Servico;
import com.jpsoftwares.katana.service.ServicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Serviços")
@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<Servico>> getAll() {
        List<Servico> servicos = servicoService.findAll();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> getById(@PathVariable Long id) {
        Servico servico = servicoService.findById(id);
        if (servico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servico);
    }

    @PostMapping
    public ResponseEntity<Servico> create(@RequestBody Servico servico) {
        Servico created = servicoService.create(servico);
        return ResponseEntity.ok(created);
    }

    @PutMapping
    public ResponseEntity<Servico> update(@RequestBody Servico servico) {
        Servico updated = servicoService.update(servico);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = servicoService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
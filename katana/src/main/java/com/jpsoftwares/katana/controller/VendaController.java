package com.jpsoftwares.katana.controller;

import com.jpsoftwares.katana.modelo.Venda;
import com.jpsoftwares.katana.service.VendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Venda")
@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    private final VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    public ResponseEntity<List<Venda>> getAll() {
        List<Venda> vendas = vendaService.findAll();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getById(@PathVariable Long id) {
        Venda venda = vendaService.findById(id);
        if (venda == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venda);
    }

    @PostMapping
    public ResponseEntity<Venda> create(@RequestBody Venda venda) {
        Venda created = vendaService.create(venda);
        return ResponseEntity.ok(created);
    }

    @PutMapping
    public ResponseEntity<Venda> update(@RequestBody Venda venda) {
        Venda updated = vendaService.update(venda);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = vendaService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}


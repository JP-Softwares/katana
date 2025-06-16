package com.jpsoftwares.katana.controller;

import com.jpsoftwares.katana.DTO.AuthDTO.RegisterDTO;
import com.jpsoftwares.katana.DTO.ProfissionalDTO.CreateProfissionalDTO;
import com.jpsoftwares.katana.model.Empresa;
import com.jpsoftwares.katana.model.Profissional;
import com.jpsoftwares.katana.service.EmpresaService;
import com.jpsoftwares.katana.service.ProfissionalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Profissional")
@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    @Autowired
    private EmpresaService empresaService;

    private final ProfissionalService profissionalService;

    @Autowired
    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> getAll() {
        List<Profissional> profissionais = profissionalService.findAll();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> getById(@PathVariable Long id) {
        Profissional profissional = profissionalService.findById(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profissional);
    }

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid CreateProfissionalDTO data){
        String senhaSecreta = new BCryptPasswordEncoder().encode(data.senha());
        Profissional profissional = new Profissional(data.nome(), data.email(), senhaSecreta, data.role(), data.telefone(), true, empresaService.findById(data.empresa()) );
        this.profissionalService.create(profissional);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Profissional> update(@RequestBody Profissional profissional) {
        Profissional updated = profissionalService.update(profissional);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = profissionalService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}



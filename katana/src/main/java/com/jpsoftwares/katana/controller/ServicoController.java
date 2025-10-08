package com.jpsoftwares.katana.controller;

import com.jpsoftwares.katana.DTO.ProdutoDTO.ProdrutoCreateDTO;
import com.jpsoftwares.katana.DTO.ProdutoDTO.ProdutoReturnDTO;
import com.jpsoftwares.katana.DTO.ServicoDTO.ServicoCreateDTO;
import com.jpsoftwares.katana.DTO.ServicoDTO.ServicoReturnDTO;
import com.jpsoftwares.katana.model.Produto;
import com.jpsoftwares.katana.model.Profissional;
import com.jpsoftwares.katana.model.Servico;
import com.jpsoftwares.katana.service.ProfissionalService;
import com.jpsoftwares.katana.service.ServicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Serviços")
@RestController
@RequestMapping("/api/servicos")
public class ServicoController {


    @Autowired
    private final ServicoService servicoService;

    @Autowired
    private  ProfissionalService profissionalService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<ServicoReturnDTO>> getAll(Authentication authentication) {

        String email = authentication.getName();
        Profissional prof = (Profissional) profissionalService.findByLogin(email);

        List<Servico> servicos = servicoService.findByEmpresa(prof.getEmpresa());

        List<ServicoReturnDTO> dtos = servicos.stream()
                .map(serv -> new ServicoReturnDTO(
                        serv.getId(),
                        serv.getNome(),
                        serv.getDescricao(),
                        serv.getValor(),
                        serv.getAtivo()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
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
    public ResponseEntity<ServicoReturnDTO> create(@RequestBody ServicoCreateDTO servico, Authentication authentication) {

        String email = authentication.getName();
        Profissional prof = (Profissional) profissionalService.findByLogin(email);

        Servico created = new Servico(servico.nome(), servico.descricao(), servico.valor(), true, prof.getEmpresa());
        servicoService.create(created);

        ServicoReturnDTO dto = new ServicoReturnDTO(
                created.getId(),
                created.getNome(),
                created.getDescricao(),
                created.getValor(),
                created.getAtivo()
        );
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoReturnDTO> update(@RequestBody ServicoCreateDTO servico, @PathVariable Long id) {
        Servico updated = servicoService.update(id, servico);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        ServicoReturnDTO dto = new ServicoReturnDTO(
                updated.getId(),
                updated.getNome(),
                updated.getDescricao(),
                updated.getValor(),
                updated.getAtivo()
        );
        return ResponseEntity.ok(dto);
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
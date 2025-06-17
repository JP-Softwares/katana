package com.jpsoftwares.katana.controller;

import com.jpsoftwares.katana.DTO.ProdutoDTO.ProdrutoCreateDTO;
import com.jpsoftwares.katana.DTO.ProdutoDTO.ProdutoReturnDTO;
import com.jpsoftwares.katana.model.Produto;
import com.jpsoftwares.katana.model.Profissional;
import com.jpsoftwares.katana.service.EmpresaService;
import com.jpsoftwares.katana.service.ProdutoService;
import com.jpsoftwares.katana.service.ProfissionalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Produto")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private final ProdutoService produtoService;


    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoReturnDTO>> getAll() {
        List<Produto> produtos = produtoService.findAll();

        List<ProdutoReturnDTO> dtos = produtos.stream()
                .map(prod -> new ProdutoReturnDTO(
                        prod.getId(),
                        prod.getNome(),
                        prod.getDescricao(),
                        prod.getValor(),
                        prod.getAtivo()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoReturnDTO> create(@RequestBody ProdrutoCreateDTO produto, Authentication authentication) {

        String email = authentication.getName();
        Profissional prof = (Profissional) profissionalService.findByLogin(email);

        Produto created = new Produto(produto.nome(), produto.descricao(), produto.valor(),true, prof.getEmpresa());
        produtoService.create(created);

        ProdutoReturnDTO dto = new ProdutoReturnDTO(
                created.getId(),
                created.getNome(),
                created.getDescricao(),
                created.getValor(),
                created.getAtivo()
        );
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping
    public ResponseEntity<Produto> update(@RequestBody Produto produto) {
        Produto updated = produtoService.update(produto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = produtoService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

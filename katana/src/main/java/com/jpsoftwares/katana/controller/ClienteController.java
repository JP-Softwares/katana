package com.jpsoftwares.katana.controller;

import com.jpsoftwares.katana.DTO.ClienteDTO.ClienteCreateDTO;
import com.jpsoftwares.katana.DTO.ClienteDTO.ClienteResponseDTO;
import com.jpsoftwares.katana.modelo.Cliente;
import com.jpsoftwares.katana.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cliente")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClienteController(ClienteService clienteService, PasswordEncoder passwordEncoder) {
        this.clienteService = clienteService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(
            @Valid @RequestBody ClienteCreateDTO dto) {

        // 1) Converter DTO de criação em entidade
        Cliente entidade = Cliente.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                // aplicar hash na senha
                .senha(passwordEncoder.encode(dto.getSenha()))
                .ativo(true)
                .build();

        // 2) Salvar
        Cliente salvo = clienteService.create(entidade);

        // 3) Converter entidade salva em DTO de resposta
        ClienteResponseDTO resposta = new ClienteResponseDTO();
        resposta.setId(salvo.getId());
        resposta.setNome(salvo.getNome());
        resposta.setEmail(salvo.getEmail());

        // 4) Retornar 200 OK com o DTO
        return ResponseEntity.ok(resposta);
    }

    @PutMapping
    public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
        Cliente updated = clienteService.update(cliente);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = clienteService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}


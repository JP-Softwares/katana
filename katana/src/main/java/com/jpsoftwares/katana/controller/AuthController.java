package com.jpsoftwares.katana.controller;


import com.jpsoftwares.katana.modelo.AuthRequest;
import com.jpsoftwares.katana.modelo.Cliente;
import com.jpsoftwares.katana.service.ClienteDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticação")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final ClienteDetailsService clienteService;

    @Autowired
    public AuthController(AuthenticationManager authManager,
                          ClienteDetailsService clienteService) {
        this.authManager = authManager;
        this.clienteService = clienteService;
    }

    // Cadastro de novo cliente
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Cliente dto) {
        Cliente criado = clienteService.register(dto);
        criado.setSenha(null);
        return ResponseEntity.ok(criado);
    }

    // Login com email e senha
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );
        // Se usar JWT, gere o token aqui e retorne-o
        return ResponseEntity.ok("Autenticado com sucesso");
    }
}

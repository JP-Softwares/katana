package com.jpsoftwares.katana.controller;


import com.jpsoftwares.katana.DTO.AuthDTO.AuthenticationDTO;
import com.jpsoftwares.katana.DTO.ProfissionalDTO.CreateProfissionalDTO;
import com.jpsoftwares.katana.modelo.Profissional;
import com.jpsoftwares.katana.service.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfissionalService profissionalService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userSenha = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userSenha);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/Register")
    public ResponseEntity register(@RequestBody @Valid CreateProfissionalDTO data){
        String senhaSecreta = new BCryptPasswordEncoder().encode(data.senha());
        Profissional profissional = new Profissional(data.nome(), data.email(), senhaSecreta, data.role(), data.telefone());
        this.profissionalService.create(profissional);
        return ResponseEntity.ok().build();
    }
}

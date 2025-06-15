package com.jpsoftwares.katana.controller;


import com.jpsoftwares.katana.DTO.AuthDTO.AuthenticationDTO;
import com.jpsoftwares.katana.DTO.AuthDTO.LoginResponseDTO;
import com.jpsoftwares.katana.DTO.ProfissionalDTO.CreateProfissionalDTO;
import com.jpsoftwares.katana.config.TokenService;
import com.jpsoftwares.katana.modelo.Profissional;
import com.jpsoftwares.katana.service.ProfissionalService;
import io.swagger.v3.oas.annotations.tags.Tag;
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


@Tag(name = "Autenticação")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userSenha = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userSenha);

        var token = tokenService.generateToken((Profissional) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CreateProfissionalDTO data){
        String senhaSecreta = new BCryptPasswordEncoder().encode(data.senha());
        Profissional profissional = new Profissional(data.nome(), data.email(), senhaSecreta, data.role(), data.telefone(), true);
        this.profissionalService.create(profissional);
        return ResponseEntity.ok().build();
    }
}

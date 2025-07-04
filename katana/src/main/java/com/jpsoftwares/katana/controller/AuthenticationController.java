package com.jpsoftwares.katana.controller;


import com.jpsoftwares.katana.DTO.AuthDTO.AuthenticationDTO;
import com.jpsoftwares.katana.DTO.AuthDTO.LoginResponseDTO;
import com.jpsoftwares.katana.DTO.AuthDTO.RegisterDTO;
import com.jpsoftwares.katana.DTO.EmpresaDTO.BasicEmpresaDTO;
import com.jpsoftwares.katana.DTO.ProfissionalDTO.ProfissionalReturnDTO;
import com.jpsoftwares.katana.config.TokenService;
import com.jpsoftwares.katana.model.Empresa;
import com.jpsoftwares.katana.model.Profissional;
import com.jpsoftwares.katana.service.EmpresaService;
import com.jpsoftwares.katana.service.ProfissionalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


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

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var userSenha = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userSenha);
        var token = tokenService.generateToken((Profissional) auth.getPrincipal());
        Profissional profissional = (Profissional) profissionalService.findByLogin(data.login());
        return ResponseEntity.ok(new LoginResponseDTO(token, profissional.getId(),profissional.getNome(),profissional.getEmail(), "Profissional", profissional.getRole()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        String senhaSecreta = new BCryptPasswordEncoder().encode(data.senha());
        Empresa business = empresaService.create(data.empresa());
        Profissional profissional = new Profissional(data.nome(), data.email(), senhaSecreta, data.role(), data.telefone(), true, business );
        this.profissionalService.create(profissional);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<ProfissionalReturnDTO> me(Authentication authentication){

        String email = authentication.getName();

        // 2) Buscar a entidade Profissional
        Profissional prof = (Profissional) profissionalService.findByLogin(email);


        // 3) Mapear para DTO
        BasicEmpresaDTO empresaDTO = new BasicEmpresaDTO(
                prof.getEmpresa().getId(),
                prof.getEmpresa().getCnpj(),
                prof.getEmpresa().getNomeFantasia(),
                prof.getEmpresa().getRazaoSocial(),
                prof.getEmpresa().getEstado(),
                prof.getEmpresa().getCidade(),
                prof.getEmpresa().getComplemento(),
                prof.getEmpresa().getCEP(),
                prof.getEmpresa().getBairro()
        );

        ProfissionalReturnDTO dto = new ProfissionalReturnDTO(
                prof.getId(),
                prof.getNome(),
                prof.getEmail(),
                prof.getRole(),
                "Profissional",
                empresaDTO
        );
        return ResponseEntity.ok(dto);
    }
}

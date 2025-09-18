package com.jpsoftwares.katana.controller;

import com.jpsoftwares.katana.DTO.AgendamentoDTO.AgendamentoCreateDTO;
import com.jpsoftwares.katana.DTO.AgendamentoDTO.AgendamentoReturnDTO;
import com.jpsoftwares.katana.DTO.ServicoDTO.ServicoReturnDTO;
import com.jpsoftwares.katana.model.Agendamento;
import com.jpsoftwares.katana.model.Profissional;
import com.jpsoftwares.katana.service.AgendamentoService;
import com.jpsoftwares.katana.service.ClienteService;
import com.jpsoftwares.katana.service.ProfissionalService;
import com.jpsoftwares.katana.service.ServicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Agendamentos")
@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private final AgendamentoService agendamentoService;


    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoReturnDTO>> getAll(Authentication authentication) {

        String email = authentication.getName();
        Profissional prof = (Profissional) profissionalService.findByLogin(email);


        List<Agendamento> agendamentos = agendamentoService.findByServico_Empresa(prof.getEmpresa());

        List<AgendamentoReturnDTO> dtos = agendamentos.stream()
                .map(agend -> new AgendamentoReturnDTO(
                        agend.getId(),
                        agend.getDataHoraInicial(),
                        agend.getDataHoraFinal(),
                        agend.getStatus(),
                        agend.getCliente().getId(),
                        agend.getServico().getId()
                ))
                .collect(Collectors.toList());


        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoReturnDTO> getById(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.findById(id);
        if (agendamento == null) {
            return ResponseEntity.notFound().build();
        }
        AgendamentoReturnDTO dto = new AgendamentoReturnDTO(
                agendamento.getId(),
                agendamento.getDataHoraInicial(),
                agendamento.getDataHoraFinal(),
                agendamento.getStatus(),
                agendamento.getCliente().getId(),
                agendamento.getServico().getId()
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AgendamentoReturnDTO> create(@RequestBody AgendamentoCreateDTO agendamento) {
        Agendamento created = new Agendamento(agendamento.dataHoraInicial(),agendamento.dataHoraFinal(),"Solicitado", clienteService.findById(agendamento.cliente_id())  , servicoService.findById(agendamento.servico_id())  );
        agendamentoService.create(created);

        AgendamentoReturnDTO dto = new AgendamentoReturnDTO(created.getId(),created.getDataHoraInicial(), created.getDataHoraFinal(),created.getStatus(),created.getCliente().getId(),created.getServico().getId());
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping
    public ResponseEntity<AgendamentoReturnDTO> update(@RequestBody AgendamentoCreateDTO agendamento, @PathVariable Long id) {
        Agendamento updated = agendamentoService.update(id, agendamento);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        AgendamentoReturnDTO dto = new AgendamentoReturnDTO(
                updated.getId(),
                updated.getDataHoraInicial(),
                updated.getDataHoraFinal(),
                updated.getStatus(),
                updated.getCliente().getId(),
                updated.getServico().getId()
        );
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = agendamentoService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

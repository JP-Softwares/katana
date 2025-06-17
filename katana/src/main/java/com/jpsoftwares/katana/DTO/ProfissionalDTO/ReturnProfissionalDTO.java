package com.jpsoftwares.katana.DTO.ProfissionalDTO;

import com.jpsoftwares.katana.DTO.EmpresaDTO.BasicEmpresaDTO;
import com.jpsoftwares.katana.model.Roles;

public record ReturnProfissionalDTO(Long id, String nome, String email, Roles role, String tipo, Long id_empresa , String cnpj, String nomeFantasia, String razaoSocial, String estado, String cidade, String complemento, String bairro, String CEP, BasicEmpresaDTO basicEmpresaDTO) {
}

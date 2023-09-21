package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.CitaPaciente;

public interface ICitaPacienteDao extends JpaRepository<CitaPaciente, Long> {

}

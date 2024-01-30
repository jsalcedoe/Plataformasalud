package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.DiagnosticoAtencion;

public interface IDiagnosticoAtencionDao extends JpaRepository<DiagnosticoAtencion, Long> {

}

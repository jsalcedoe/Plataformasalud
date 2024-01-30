package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.ProcedimientosExamenes;

public interface IProcedimientoExamenesDao extends JpaRepository<ProcedimientosExamenes, Long> {

}

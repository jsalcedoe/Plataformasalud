package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.ProcedimientoQuirurgico;

public interface IProcedimientoQuirurgicoDao extends JpaRepository<ProcedimientoQuirurgico, Long> {

}

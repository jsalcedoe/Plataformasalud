package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.OrdenProcedimientoExamen;

public interface IOrdenProcedimientoExamenDao extends JpaRepository<OrdenProcedimientoExamen, Long> {

}

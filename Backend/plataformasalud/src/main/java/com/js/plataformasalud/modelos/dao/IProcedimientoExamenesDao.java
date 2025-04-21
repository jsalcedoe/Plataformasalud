package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.js.plataformasalud.modelos.entidades.ProcedimientosExamenes;

public interface IProcedimientoExamenesDao extends JpaRepository<ProcedimientosExamenes, Long> {
	
	@Query("SELECT p FROM ProcedimientosExamenes p WHERE LOWER(p.nompxex) LIKE LOWER(CONCAT('%', :nompxex, '%'))")
    List<ProcedimientosExamenes> findBynompxex(@Param("nompxex") String nompxex);

}

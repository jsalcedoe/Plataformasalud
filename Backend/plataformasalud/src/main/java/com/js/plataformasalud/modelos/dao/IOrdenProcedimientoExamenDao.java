package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.js.plataformasalud.modelos.entidades.OrdenProcedimientoExamen;

public interface IOrdenProcedimientoExamenDao extends JpaRepository<OrdenProcedimientoExamen, Long> {
	@Query("select op from OrdenProcedimientoExamen op where op.eventordprocexam_fk.idevent = :idevent")
	List<OrdenProcedimientoExamen> findByEventpordprocexam_fk(@Param("idevent")Long idevent);

}
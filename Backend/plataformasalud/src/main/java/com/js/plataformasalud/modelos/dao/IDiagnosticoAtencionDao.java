package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.DiagnosticoAtencion;

public interface IDiagnosticoAtencionDao extends JpaRepository<DiagnosticoAtencion, Long> {

	@Query("select d from DiagnosticoAtencion d where d.eventdxate_fk.idevent = :idevent")
	List<DiagnosticoAtencion> findByEventdxate_Fk(@Param("idevent")Long idevent);

}

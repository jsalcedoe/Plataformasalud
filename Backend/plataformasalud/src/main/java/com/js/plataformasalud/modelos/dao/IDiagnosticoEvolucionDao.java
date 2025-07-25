package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.DiagnosticoEvolucion;


public interface IDiagnosticoEvolucionDao extends JpaRepository<DiagnosticoEvolucion, Long> {
	@Query("SELECT d FROM DiagnosticoEvolucion d WHERE d.evopac_fk.idevol = :idevol")
	List<DiagnosticoEvolucion> findByEvoFkId(@Param("idevol") Long idevol);

}

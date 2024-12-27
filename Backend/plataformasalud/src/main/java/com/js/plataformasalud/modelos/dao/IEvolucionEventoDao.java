package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.EvolucionEvento;

public interface IEvolucionEventoDao extends JpaRepository<EvolucionEvento, Long> {

	@Query("select e from EvolucionEvento e where e.eventevo_fk.idevent = :idevent")
	List<EvolucionEvento> findByEventevo_Fk(@Param("idevent")Long idevent);
	
}

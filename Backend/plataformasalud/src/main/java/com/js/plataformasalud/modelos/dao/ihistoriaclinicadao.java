package com.js.plataformasalud.modelos.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.HistoriaClinica;

public interface IHistoriaClinicaDao extends JpaRepository<HistoriaClinica, Long> {

	@Query("select h from HistoriaClinica h where h.eventpac_fk.idevent = :idevent")
	HistoriaClinica findByEventpac_Fk(@Param("idevent")Long idevent);
	

}

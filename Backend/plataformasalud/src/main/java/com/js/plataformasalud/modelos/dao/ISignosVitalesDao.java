package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.SignosVitales;

public interface ISignosVitalesDao extends JpaRepository<SignosVitales, Long>{
	
	@Query("select s from SignosVitales s where s.eventsigvit_fk.idevent = :idevent")
	List<SignosVitales> findByEventsigvit_Fk(@Param("idevent")Long idevent);

}

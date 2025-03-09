package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.ConsentimientoPaciente;

public interface IConsentimientoPacienteDao extends JpaRepository<ConsentimientoPaciente, Long> {
	@Query("select c from ConsentimientoPaciente c where c.eventconsinfpac_fk.idevent = :idevent")
	List<ConsentimientoPaciente> findByEventconsinfpac_fk(@Param("idevent")Long idevent);

}

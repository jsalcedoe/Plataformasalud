package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.RegistroMedicamento;

public interface IRegistroMedicamentoDao extends JpaRepository<RegistroMedicamento, Long> {
	@Query("select r from RegistroMedicamento r where r.eventoregistromedins_fk.idevent = :idevent")
	List<RegistroMedicamento> findByEventoregistromedins_Fk (@Param("idevent")Long idevent);

}

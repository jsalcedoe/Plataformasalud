package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.DiagnosticoHistoriaClinica;

public interface IDiagnosticoHistoriaClinicaDao extends JpaRepository<DiagnosticoHistoriaClinica, Long>{
	@Query("SELECT d FROM DiagnosticoHistoriaClinica d WHERE d.hcpac_fk.idhcpac = :idhcpac")
	List<DiagnosticoHistoriaClinica> findByHcpacFkId(@Param("idhcpac") Long idhcpac);
}

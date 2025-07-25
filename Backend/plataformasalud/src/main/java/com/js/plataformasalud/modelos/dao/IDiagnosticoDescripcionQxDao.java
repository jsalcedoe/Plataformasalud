package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;
import com.js.plataformasalud.modelos.entidades.DiagnosticoDescripcionQx;


public interface IDiagnosticoDescripcionQxDao extends JpaRepository<DiagnosticoDescripcionQx, Long> {
	
	@Query("SELECT dx FROM DiagnosticoDescripcionQx dx WHERE dx.desqx_fk = :desqx")
	List<DiagnosticoDescripcionQx> findByDxqxFk (@Param("desqx")DescripcionQuirurgica desqx_fk);

	

}

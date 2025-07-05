package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.js.plataformasalud.modelos.entidades.TipoDiagnostico;

public interface ITipoDiagnosticoDao extends JpaRepository<TipoDiagnostico, Long> {
	@Query("SELECT t FROM TipoDiagnostico t WHERE LOWER(t.detypdx) LIKE LOWER(CONCAT('%', :term, '%'))")
	List<TipoDiagnostico> findByDetypdx(@Param("term") String term);

}

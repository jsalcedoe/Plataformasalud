package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.TipoNota;

public interface ITipoNotaDao extends JpaRepository<TipoNota, Long>{
	
	@Query("SELECT tn FROM TipoNota tn WHERE LOWER(tn.dettypnot) LIKE LOWER(CONCAT('%', :dettypnot, '%'))")
    List<TipoNota> findByTipoNota(@Param("dettypnot") String dettypnot);
	

}

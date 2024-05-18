package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.Diagnostico;

public interface IDxDao extends JpaRepository<Diagnostico, Long>{
	 @Query("SELECT d FROM Diagnostico d WHERE LOWER(d.nomdx) LIKE LOWER(CONCAT('%', :term, '%'))")
	    List<Diagnostico> findByNomdx(@Param("term") String term);

}

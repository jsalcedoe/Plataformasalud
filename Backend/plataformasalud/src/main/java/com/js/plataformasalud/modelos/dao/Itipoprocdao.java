package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.js.plataformasalud.modelos.entidades.TipoProcedimiento;

public interface Itipoprocdao extends JpaRepository<TipoProcedimiento, Long> {
	@Query("SELECT tpe FROM TipoProcedimiento tpe WHERE LOWER(tpe.detproc) LIKE LOWER(CONCAT('%', :detproc, '%'))")
    List<TipoProcedimiento> findByTipoProcexam(@Param("detproc") String detproc);

}

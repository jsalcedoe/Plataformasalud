package com.js.plataformasalud.modelos.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;


public interface IDescripcionQuirurgicaDao extends JpaRepository<DescripcionQuirurgica, Long> {
	@Query("SELECT d from DescripcionQuirurgica d where d.eventpxqx_fk.idevent = :idevent")
	List<DescripcionQuirurgica> findByEventpxqx_Fk(@Param ("idevent") Long idevent);

}

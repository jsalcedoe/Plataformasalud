package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;
import com.js.plataformasalud.modelos.entidades.ProcedimientoDescripcionQX;

public interface IProcedimientoDescripcionQXDao extends JpaRepository<ProcedimientoDescripcionQX, Long>{
	@Query("select p from ProcedimientoDescripcionQX p where p.descqx_fk = :desqx")
	List<ProcedimientoDescripcionQX> findByDescqx_Fk(@Param("desqx")DescripcionQuirurgica descqx_fk);


}

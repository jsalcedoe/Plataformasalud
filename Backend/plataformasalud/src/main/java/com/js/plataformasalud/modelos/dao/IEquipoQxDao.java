package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;
import com.js.plataformasalud.modelos.entidades.EquipoQx;

public interface IEquipoQxDao extends JpaRepository<EquipoQx, Long>{
	@Query("SELECT e FROM EquipoQx e WHERE e.desqx_fk = :desqx")
	List<EquipoQx> findByDesqxFk (@Param("desqx")DescripcionQuirurgica desqx_fk);

}

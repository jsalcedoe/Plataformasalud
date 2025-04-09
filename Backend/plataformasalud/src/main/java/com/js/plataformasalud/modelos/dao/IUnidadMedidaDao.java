package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.UnidadMedida;

public interface IUnidadMedidaDao extends JpaRepository<UnidadMedida, Long> {
	
	@Query("SELECT u FROM UnidadMedida u WHERE LOWER(u.detunimedi) LIKE LOWER(CONCAT('%', :detunimedi, '%'))")
    List<UnidadMedida> findByUmedins(@Param("detunimedi") String detunimedi);

}

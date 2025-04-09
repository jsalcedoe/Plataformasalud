package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.PresentacionMedicamentoInsumo;

public interface IPresentacionMedicamentoInsumoDao extends JpaRepository<PresentacionMedicamentoInsumo, Long> {
	
	@Query("SELECT p FROM PresentacionMedicamentoInsumo p WHERE LOWER(p.detpmedins) LIKE LOWER(CONCAT('%', :detpmedins, '%'))")
    List<PresentacionMedicamentoInsumo> findByPmedins(@Param("detpmedins") String detpmedins);

}

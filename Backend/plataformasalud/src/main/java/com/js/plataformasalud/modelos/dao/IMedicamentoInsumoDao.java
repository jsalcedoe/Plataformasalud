package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.MedicamentoInsumo;

public interface IMedicamentoInsumoDao extends JpaRepository<MedicamentoInsumo, Long> {
	
	@Query("SELECT m FROM MedicamentoInsumo m WHERE LOWER(m.medins) LIKE LOWER(CONCAT('%', :medins, '%'))")
    List<MedicamentoInsumo> findByMedins(@Param("medins") String medins);
}


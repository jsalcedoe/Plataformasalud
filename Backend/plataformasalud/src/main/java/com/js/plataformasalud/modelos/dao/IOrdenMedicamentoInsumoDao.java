package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.OrdenMedicamentoInsumo;

public interface IOrdenMedicamentoInsumoDao extends JpaRepository<OrdenMedicamentoInsumo, Long> {
	@Query("select o from OrdenMedicamentoInsumo o where o.eventordmedins_fk.idevent = :idevent")
	List<OrdenMedicamentoInsumo> findByEventordmedins_fk(@Param("idevent")Long idevent);
}

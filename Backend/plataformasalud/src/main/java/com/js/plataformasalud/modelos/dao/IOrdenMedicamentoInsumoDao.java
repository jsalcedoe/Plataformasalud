package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.OrdenMedicamentoInsumo;

public interface IOrdenMedicamentoInsumoDao extends JpaRepository<OrdenMedicamentoInsumo, Long> {

}

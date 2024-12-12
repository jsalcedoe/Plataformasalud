package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.PresentacionMedicamentoInsumo;

public interface IPresentacionMedicamentoInsumoDao extends JpaRepository<PresentacionMedicamentoInsumo, Long> {

}

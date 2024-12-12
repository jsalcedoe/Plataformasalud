package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.TipoMedicamentoInsumo;

public interface ITipoMedicamentoInsumoDao extends JpaRepository<TipoMedicamentoInsumo, Long> {

}

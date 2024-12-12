package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.FabricanteMedicamentoInsumo;

public interface IFabricanteMedicamentoInsumoDao extends JpaRepository<FabricanteMedicamentoInsumo, Long> {

}

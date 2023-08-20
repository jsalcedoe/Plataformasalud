package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.TarifaContrato;

public interface ITarifaContratoDao extends JpaRepository<TarifaContrato, Long> {

}

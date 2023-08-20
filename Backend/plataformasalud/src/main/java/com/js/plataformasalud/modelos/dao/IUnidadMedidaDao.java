package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.UnidadMedidaMedIns;

public interface IUnidadMedidaDao extends JpaRepository<UnidadMedidaMedIns, Long> {

}

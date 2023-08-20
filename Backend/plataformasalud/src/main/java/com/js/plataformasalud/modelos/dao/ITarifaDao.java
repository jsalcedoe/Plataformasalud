package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Tarifa;

public interface ITarifaDao extends JpaRepository<Tarifa, Long> {

}

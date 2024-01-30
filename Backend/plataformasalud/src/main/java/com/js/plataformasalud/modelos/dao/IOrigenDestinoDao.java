package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.OrigenDestino;

public interface IOrigenDestinoDao extends JpaRepository<OrigenDestino, Long> {

}

package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Cargo;

public interface ICargoDao extends JpaRepository<Cargo, Long> {

}

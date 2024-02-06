package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Camas;

public interface ICamasDao extends JpaRepository<Camas, Long> {

}

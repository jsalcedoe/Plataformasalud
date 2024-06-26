package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Conducta;

public interface IConductaDao extends JpaRepository<Conducta, Long> {

}

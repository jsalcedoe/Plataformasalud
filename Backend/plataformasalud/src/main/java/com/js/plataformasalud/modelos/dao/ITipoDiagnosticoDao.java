package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.TipoDiagnostico;

public interface ITipoDiagnosticoDao extends JpaRepository<TipoDiagnostico, Long> {

}

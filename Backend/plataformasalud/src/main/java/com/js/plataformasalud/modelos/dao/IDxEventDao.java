package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.DiagnosticoEvent;

public interface IDxEventDao extends JpaRepository<DiagnosticoEvent, Long>{

}

package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.HistoriaClinica;

public interface IHistoriaClinicaDao extends JpaRepository<HistoriaClinica, Long> {

}

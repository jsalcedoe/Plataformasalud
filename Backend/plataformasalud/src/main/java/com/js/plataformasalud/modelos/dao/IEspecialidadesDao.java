package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Especialidades;

public interface IEspecialidadesDao extends JpaRepository<Especialidades, Long> {

}

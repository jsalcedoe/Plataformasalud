package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Consultorio;

public interface IConsultorioDao extends JpaRepository<Consultorio, Long>{

}

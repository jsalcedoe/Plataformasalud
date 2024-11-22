package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.SignosVitales;

public interface ISignosVitalesDao extends JpaRepository<SignosVitales, Long>{

}

package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Plantillas;

public interface IPlantillaDao extends JpaRepository<Plantillas, Long> {

}

package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Departamento;

public interface IDepartamentoDao extends JpaRepository<Departamento, Long> {

}

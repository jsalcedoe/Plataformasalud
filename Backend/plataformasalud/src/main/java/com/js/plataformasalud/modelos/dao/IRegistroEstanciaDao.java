package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.RegistroEstancia;

public interface IRegistroEstanciaDao extends JpaRepository<RegistroEstancia, Long> {

}

package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.TipoProcedimiento;

public interface Itipoprocdao extends JpaRepository<TipoProcedimiento, Long> {

}

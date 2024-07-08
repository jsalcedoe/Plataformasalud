package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;

public interface IProcedimientoQuirurgicoDao extends JpaRepository<DescripcionQuirurgica, Long> {

}

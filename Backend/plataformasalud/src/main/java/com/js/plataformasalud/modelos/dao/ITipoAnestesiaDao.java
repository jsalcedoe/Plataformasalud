package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.TipoAnestesia;

public interface ITipoAnestesiaDao extends JpaRepository<TipoAnestesia, Long>{

}

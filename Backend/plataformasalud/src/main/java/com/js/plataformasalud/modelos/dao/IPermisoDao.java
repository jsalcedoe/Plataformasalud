package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Permiso;

public interface IPermisoDao extends JpaRepository<Permiso, Long>{

}

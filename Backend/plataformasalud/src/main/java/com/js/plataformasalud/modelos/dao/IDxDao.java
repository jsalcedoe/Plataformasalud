package com.js.plataformasalud.modelos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Diagnostico;

public interface IDxDao extends JpaRepository<Diagnostico, Long>{
	List<Diagnostico> findByNomdx(String nomdx);

}

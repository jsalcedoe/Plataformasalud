package com.js.plataformasalud.modelos.dao;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.js.plataformasalud.modelos.entidades.Evento;

public interface IEventoDao extends JpaRepository<Evento, Long> {
	/*@Query("SELECT e FROM Evento e WHERE e.pacevent_fk.numdocpac = ?1")
    List<Evento> findByPaceventFkNumdocpac(String numDocPac);*/
	
}

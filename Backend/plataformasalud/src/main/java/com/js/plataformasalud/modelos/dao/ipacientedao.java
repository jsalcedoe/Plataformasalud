package com.js.plataformasalud.modelos.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Paciente;

public interface ipacientedao extends JpaRepository<Paciente, Long> {
	
	

}

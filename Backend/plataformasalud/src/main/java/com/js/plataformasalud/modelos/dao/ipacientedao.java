package com.js.plataformasalud.modelos.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.plataformasalud.modelos.entidades.Paciente;

public interface ipacientedao extends JpaRepository<Paciente, Long> {
	
	@Query("SELECT p FROM Paciente p WHERE LOWER(p.primernompac) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.numdocpac) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Paciente> buscarPorNombreODocumento(@Param("keyword") String keyword);

}

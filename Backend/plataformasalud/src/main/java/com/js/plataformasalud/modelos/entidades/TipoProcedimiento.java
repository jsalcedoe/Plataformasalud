package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tproc")

public class TipoProcedimiento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idtproc;
	
	@Column(nullable = false)
	private String nomtproc; 
	
	/*Los tipos de procedimientos pueden ser:
	 1. quirurgicos
	 2. Laboratorio
	 3. Ecografias
	 4. Rx
	 5. procedimientos no invasivos*/
	
	private String estado;
	
	/*Los estados pueden ser: creado, modificado,eliminado*/
	 
	private static final long serialVersionUID = 1L;

}

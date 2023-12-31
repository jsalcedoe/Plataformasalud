package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "consultorios")


public class Consultorio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long idconsultorio;
	
	@NotEmpty(message = "El campo del consultorio u area a crear no puede estar vacio")
	private String nomconsul;
	
	private String estado;
	/* el estado puede ser creado, modificado o eliminado*/
	
	private static final long serialVersionUID = 1L;

}

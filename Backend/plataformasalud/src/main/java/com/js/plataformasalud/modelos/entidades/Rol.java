package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="roles")
@Getter
@Setter
public class Rol implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idrol;
	
	@Column(unique = true,length = 20)
	private String nomrol;
	
	@Column(unique = true, length = 50)
	private String desrol; // realiza la descripcion detallada del rol que se esta creando

	
	private static final long serialVersionUID = 1L;

}

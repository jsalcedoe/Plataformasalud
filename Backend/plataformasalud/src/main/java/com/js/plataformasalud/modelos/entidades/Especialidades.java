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

@Getter
@Setter

@Entity
@Table(name = "especialidades")
public class Especialidades implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idespecialidades;
	
	@Column(length = 45)
	private String especialidad;
	
	private String estado;
	//El estado puede ser creado, modificado o eliminado
	
	private static final long serialVersionUID = 1L;

}

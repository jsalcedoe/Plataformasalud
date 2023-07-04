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
@Table(name = "ubicaciones")

public class Ubicacion implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idubicaciones;
	
	@Column(nullable = false, length = 40)
	@NotEmpty(message = "El campo ubicacion no puede ser vacio, por favor ingrese la informacion de ubicacion")
	private String nomubicaciones;
	
	private String estado;
	
	private static final long serialVersionUID = 1L;

}

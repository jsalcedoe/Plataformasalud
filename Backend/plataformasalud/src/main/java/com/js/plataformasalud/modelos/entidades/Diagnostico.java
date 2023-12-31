package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dx")

public class Diagnostico implements Serializable {
	
	
	@Id
	@Column(nullable = false, length = 4)
	@NotEmpty(message = "El campo codigo del diagnostico no puede ser vacio, por favor indique el codigo del diagnostico")
	private String iddx;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo nombre del diagnostico no puede ser vacio, por favor digite el nombre del diagnostico")
	private String nomdx;
	
	private String descdx;
	
	private String estdx;// El estado puede ser: activo o inactivo
	
	private Long sexdx; // en el sexo puede ser 1. masculino, 2. femenino, 3. Ambos
	
	private long edadmindx;
	
	private long edadmaxdx;
	
	private String capdx;
	

	/*los estados pueden ser: creado, modificado, eliminado*/
	
	private static final long serialVersionUID = 1L;

}

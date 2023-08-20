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
@Table(name = "grupmedins")

public class GrupoMedIns implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long idgrupmedins;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo grupo no puede ser vacio, por favor indicar el grupo")
	private String nomgrupmedins;
	/*los grupos pueden ser:
	medicamentos
	material quirurgico
	insumos
	Entre otros */
	
	private static final long serialVersionUID = 1L;

	
	

}

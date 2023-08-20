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
@Table(name="tarifas")


public class Tarifa implements Serializable {


	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idtarifa;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo tarifa no puede estar vacio, por favor digite la tarifa")
	private String nomtarifa;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo detalle de la tarifa no puede estar vacio")
	private String detatarifa;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo de valor para la tarifa no puede estar vacio, por favor digitelo")
	private long valortarifa;
	
	private static final long serialVersionUID = 1L;

}

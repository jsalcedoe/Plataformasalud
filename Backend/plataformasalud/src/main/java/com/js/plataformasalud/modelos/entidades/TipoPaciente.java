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
@Table(name="tipac")

public class TipoPaciente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long idtipac;
	
	@Column(nullable = false, length = 15)
	private String nomtipac;
	/*
	 * 	Los tipos de pacientes pueden ser:
		contizantes
		beneficiarios
		particulares
	 */
	
	private static final long serialVersionUID = 1L;

}

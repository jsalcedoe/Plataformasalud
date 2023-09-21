package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "citpac")

public class CitaPaciente implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long idcitpac;
	
	private String motcitpac;
	
	private Boolean novcitpac; // indica si hay novedad en la cita
	
	private String detnovcitpac;// indica el detalle de la novedad en la cita, si fue cancelada o aplazada
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Agenda idagendacit;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Paciente numdocpac;


	private static final long serialVersionUID = 1L;

}

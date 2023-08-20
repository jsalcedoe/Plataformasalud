package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

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
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "agmed")

public class Agenda implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long idagmed;
	
	@Column(nullable = false)
	private Date fechaagmed;
	
	@Column(nullable = false)
	private Date horainiagmed;
	
	@Column(nullable = false)
	private Date horafinagmed;
	
	@Column(nullable = false)
	@NotEmpty(message = "El intervalo del las consultas o procedimiento no puede estar en blanco")
	private long intervtempagmed; //intervalo de tiempo para agenda medica o tiempo que se demore en cada cita
	
	private long totalcitasagmed;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Consultorio consagmed;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario mediagmed;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private ProcedimientosExamenes cupscita;
	
	private static final long serialVersionUID = 1L;

}

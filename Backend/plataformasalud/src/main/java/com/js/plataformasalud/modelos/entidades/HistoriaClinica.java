package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hcpac")

// se debe crear aparte un modulo donde se realicen las evoluciones y se realice el registro de los examenes.

public class HistoriaClinica implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idhcpac;
	
	@Temporal(TemporalType.DATE)
	private Date datecreathcpac;
	
	@PrePersist
	public void persiste() {
		datecreathcpac = new Date();
	}
	
	@Temporal(TemporalType.DATE)
	private Date datedithcpac;
	
	@PreUpdate
	public void edithcpac() {
		datedithcpac = new Date();
	}
	
	@Column(nullable = false, length = 3)
	@NotNull(message = "Por favor digite el peso en kilogramos del paciente")
	private Long pesohcpac;
	
	@Column(nullable = false, length = 4)
	@NotNull(message = "Por favor digite la estatura en centimetros del paciente")
	private Long estaturahcpac;
	
	@Column(nullable = false, length = 3)
	@NotEmpty(message = "Digite por favor la frecuencia cardiaca")
	private String fchcpac;
	
	@Column(nullable = false, length = 3)
	@NotEmpty(message = "Digitepor favor la frecuencia respiratoria")
	private String frhcpac;
	
	@Column(nullable = false, length = 3)
	@NotEmpty(message = "Digite por favor la temperatura")
	private String temphcpac;
	
	@Column(nullable = false, length = 7)
	@NotEmpty(message = "Digite por favor la tension arterial")
	private String tahcpac;
	
	@Column(nullable = false, length = 200)
	@NotEmpty(message = "Digite el motivo de consulta")
	private String motconshcpac;
	
	@Column(nullable = false, length = 200)
	@NotEmpty(message = "Digite la enfermedad actual")
	private String enfacthcpac;
	
	@Column(nullable = false, length = 200)
	@NotEmpty(message = "Digite los antecedentes patologicos del paciente")
	private String antpathcpac;
	
	@Column(nullable = false, length = 200)
	@NotEmpty(message = "Digite los antecedentes quirurgicos del paciente")
	private String antqxhcpac;
	
	@Column(nullable = false, length = 200)
	@NotEmpty(message = "Digite los antecedentes alergicos del paciente")
	private String antalerhcpac;
	
	@Column(nullable = false, length = 200)
	@NotEmpty(message = "Digite los antecedentes farmacologicos del paciente")
	private String antfarmhcpac;
	
	@Column(nullable = false, length = 200)
	@NotEmpty(message = "Digite los antecedentes familiares del paciente")
	private String antfamyhcpac;
	
	@Column(nullable = false, length = 200)
	@NotEmpty(message = "Digite los antecedentes traumaticos del paciente")
	private String anttxhcpac;
	
	@Column(nullable = false, length =  2000)
	@NotEmpty(message = "Digite el objetivo de la historia clínica")
	private String objhcpac;
	
	@Column(nullable = false, length = 2000)
	@NotEmpty(message = "Digite el analisis de la historia clínica")
	private String analisishcpac;
	
	@Column(nullable = false, length = 5000)
	@NotEmpty(message = "Registre el plan de manejo para el paciente")
	private String planmanejhcpac;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario medico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado esthcpac_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventpac_fk;
	
	private static final long serialVersionUID = 1L;

}

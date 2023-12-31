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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hcpac")

public class HistoriaClinica implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idhcpac;
	
	@Column(nullable = false, length = 3)
	@NotEmpty(message = "Por favor digite el peso en kilogramos del paciente")
	private long pesohcpac;
	
	@Column(nullable = false, length = 4)
	@NotEmpty(message = "Por favor digite la estatura en centimetros del paciente")
	private long estaturahcpac;
	
	@Column(nullable = false, length = 6)
	private long imchcpac;
	
	@PrePersist
	@PreUpdate
	private void calculaIMC() {
		long alturametros = estaturahcpac/100;
		imchcpac = pesohcpac/(alturametros*alturametros);
		
	}
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
	
	@Column(nullable = false)
	@NotEmpty(message = "Digite el motivo de consulta")
	private String motconshcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Digite la enfermedad actual")
	private String enfacthcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Digite los antecedentes patologicos del paciente")
	private String antpathcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Digite los antecedentes quirurgicos del paciente")
	private String antqxhcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Digite los antecedentes alergicos del paciente")
	private String antalerhcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Digite los antecedentes farmacologicos del paciente")
	private String antfarmhcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Digite los antecedentes toxicologicos del paciente")
	private String anttoxihcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Digite los antecedentes familiares del paciente")
	private String antfamyhcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Digite el objetivo de la historia clínica")
	private String objhcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Digite el analisis de la historia clínica")
	private String analisishcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Registre por favor el los reportes de examenes con su interpretacion")
	private String repexahcpac;
	
	@Column(nullable = false)
	@NotEmpty(message = "Registre el plan de manejo para el paciente")
	private String planmanejhcpac;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario medico;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventohcpac;
	
	private String estado;
	//El estado puede ser: creado, modificado, eliminado
	
	
	
	private static final long serialVersionUID = 1L;

}

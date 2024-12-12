package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ascit")

public class AsignaCita implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idascit;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "El campo fecha no puede ser vacio")
	private Date fechacreatascit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Paciente citpac_fk;
	
	@Column(nullable = false)
	@NotNull(message = "El campo hora de la cita no puede ser vacío")
	private LocalTime horaCita;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private ProcedimientosExamenes citprcexam_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Agenda citagcit_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estcit_fk;

	
	private static final long serialVersionUID = 1L;

}

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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "desqx")

public class DescripcionQuirurgica implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long idqx;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaprocqx;
	
	@Column(nullable = false, length = 5)
	@NotNull(message = "El campo hora del procedimiento no puede ser vacio")
	private LocalTime horainicioprocqx;
	
	@Column(nullable = false, length = 5)
	@NotNull(message = "El campo hora final no puede ser vacio")
	private LocalTime horafinprocqx;
	
	private Long timeqx;
	
	@Column(nullable = false, length = 2000)
	@NotEmpty(message = "El campo descripcion no puede ser vacio")
	private String descqx;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoHerida typhxqx_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoProcedimiento typxqx_fk;
	
	private String matprot; // describe si se coloco material o protesis y si se coloca lo describe
	
	private String muespato; 
	// Descripcion de las muestras anatopatologicas si es el caso incluye el tipo la cantidad la localizacion el aspecto
	
	private String complicqx; // Describe el tipo de complicacion detalladamente
	
	private String hallaqx; // describe los hallazgos encontrados
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Conducta conducqx_fk;
	//la conducta puede ser, hospitalizar, observacion, o salida
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventpxqx_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoAnestesia anestesia_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estpxqx_fk;
	
	private static final long serialVersionUID = 1L;
	
}
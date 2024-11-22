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
@Table(name="sigvit")


public class SignosVitales implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long idsigvit;
	
	@Column(nullable = false, length = 8)
	@NotEmpty(message = "El campo tension arterial no puede ser vacio")
	private String ta;// tension arterial
	
	@Column(nullable = false, length = 3)
	@NotNull(message = "Por favor digite la frecuencia cardiaca del paciente")
	private Long fc; // frecuencia cardiaca
	
	@Column(nullable = false, length = 3)
	@NotNull(message = "Por favor digite la frecuencia respiratoria del paciente")
	private Long fr; // frecuencia respiratoria
	
	@Column(nullable = false, length = 5)
	@NotEmpty(message = "El campo saturación no puede ser vacio")
	private String sat; // saturacion
	
	@Column(nullable = false, length = 2000)
	@NotEmpty(message = "El campo temperatura no puede ser vacio")
	private String tem; // temperatura
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateregsigvit; // fecha registro signos vitales
	
	@Column(nullable = false, length = 5)
	@NotNull(message = "El campo hora del registro no puede ser vacio")
	private LocalTime houregsigvit; // hora registro signos vitales
	
	private Date dateeditregsigvit; // fecha edicion registro signos vitales
	
	private LocalTime houreditregsigvit; //hora edicion registro signos vitales
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario reguser_fk; // usuario que toma el signo vital
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventsigvit_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estsigvit_fk;
	
	private static final long serialVersionUID = 1L;
	
	
	
	


}

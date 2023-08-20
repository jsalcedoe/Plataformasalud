package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = "eventpac")

public class Evento implements Serializable {

	@Id
	private long ideventpac;
		
	@Column (nullable = false, length = 12 )
	@Temporal(TemporalType.DATE)
	@NotNull (message = "El campo fecha de ingreso no puede ser vacio, seleccione fecha de ingreso")
	private Date fechainieventpac;
	
	@PrePersist
	public void prePersis() {
		fechainieventpac = new Date();
	}
	@Column(nullable = false)
	@NotEmpty(message = "Ingrese el motivo de su asistencia al centro asistencial")
	private String moteventpac;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date fechafineventpac;
	
	private String estado;
	// los estados pueden ser: creado, modificado, eliminado
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Paciente paciente;
		
	private static final long serialVersionUID = 1L;

}
